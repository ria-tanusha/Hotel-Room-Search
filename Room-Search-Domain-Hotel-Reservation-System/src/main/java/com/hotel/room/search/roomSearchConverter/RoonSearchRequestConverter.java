package com.hotel.room.search.roomSearchConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hotel.room.reservation.model.database.Room;
import com.hotel.room.reservation.model.database.RoomsReserved;
import com.hotel.room.reservation.model.view.RoomSearchViewRequest;
import com.hotel.room.search.service.RoomService;
import com.hotel.room.search.service.RoomsReservedService;

@Service
public class RoonSearchRequestConverter {

	@Autowired
	private RoomService roomService;

	@Autowired
	private RoomsReservedService roomsReservedService;

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

	public Map<String, Map<String, Integer>> viewObjToDatabaseObjRequestConverter(
			RoomSearchViewRequest roomSearchViewRequest) {

		Map<String, Map<String, Integer>> roomMap = new HashMap<>();
		prepareRoomDetailsMapPerDay(roomSearchViewRequest, roomMap);

		return roomMap;
	}

	private void prepareRoomDetailsMapPerDay(RoomSearchViewRequest roomSearchViewRequest,
			Map<String, Map<String, Integer>> roomMap) {
		LocalDate fromDt = null;
		LocalDate toDt = null;

		fromDt = LocalDate.parse(roomSearchViewRequest.getFromDt(), formatter);
		toDt = LocalDate.parse(roomSearchViewRequest.getToDt(), formatter);

		// Iterate each day loop
		Stream.iterate(fromDt, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(fromDt, toDt) + 1)
				.forEach(date -> {
					Map<String, Integer> roomTypeMap = new HashMap<>();
					List<Room> roomList = StringUtils.isEmpty(roomSearchViewRequest.getRoomType())
							? roomService.findAll()
							: roomService.getlistFindByRoomtype(roomSearchViewRequest.getRoomType());

					// When roomType is not provided in req
					generateMapWithoutRoomType(roomSearchViewRequest, roomList, roomTypeMap, date.toString());

					// When roomType is provided in req
					generateMapWithRoomType(roomSearchViewRequest, roomList, roomTypeMap, date.toString());

					roomMap.put(date.toString(), roomTypeMap);

				});

	}

	private int getBookedRoomListDateTypeWise(String roomType, String date) {
		List<RoomsReserved> list = roomsReservedService.getListFindByRoomTypeAndReservedDate(roomType, date);
		return (list.size() > 0) ? Integer.parseInt(list.get(0).getRooms_booked()) : 0;
	}

	// When roomType is not provided in req
	private void generateMapWithoutRoomType(RoomSearchViewRequest roomSearchViewRequest, List<Room> roomList,
			Map<String, Integer> roomtypeMap, String date) {

		if (StringUtils.isEmpty(roomSearchViewRequest.getRoomType())) {
			Map<String, Integer> uniqueRoomMap = uniqueRoom(roomList);

			for (String type : uniqueRoomMap.keySet()) {
				int roomBookedCnt = getBookedRoomListDateTypeWise(type, date.toString());
				roomtypeMap.put(type, uniqueRoomMap.get(type) - roomBookedCnt);
			}
		}

	}

	// When roomType is  provided in req
	private void generateMapWithRoomType(RoomSearchViewRequest roomSearchViewRequest, List<Room> roomList,
			Map<String, Integer> roomtypeMap, String date) {

		if (Objects.nonNull(roomSearchViewRequest.getRoomType()) && roomList.size() > 0) {
			int roomBookedCnt = getBookedRoomListDateTypeWise(roomSearchViewRequest.getRoomType(), date.toString());
			roomtypeMap.put(roomSearchViewRequest.getRoomType(), roomList.size() - roomBookedCnt);
		}

	}

	private Map<String, Integer> uniqueRoom(List<Room> roomList) {
		Map<String, Integer> roomMap = new HashMap<>();
		roomList.forEach((Room room) -> {
			if (!roomMap.containsKey(room.getRoom_type())) {
				roomMap.put(room.getRoom_type(), 1);
			} else {
				roomMap.put(room.getRoom_type(), roomMap.get(room.getRoom_type()) + 1);
			}
		});
		return roomMap;
	}
}
