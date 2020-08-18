package com.hotel.room.search.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.room.search.roomSearchConverter.RoonSearchRequestConverter;
import com.hotel.room.search.roomSearchConverter.RoonSearchResponseConverter;
import com.hotel.room.search.service.ReservationService;
import com.hotel.room.search.service.RoomService;
import com.hotel.room.search.service.RoomsReservedService;
import com.room.reservation.domain.model.database.Reservation;
import com.room.reservation.domain.model.database.Room;
import com.room.reservation.domain.model.database.RoomsReserved;
import com.room.reservation.domain.model.view.RoomSearchViewRequest;
import com.room.reservation.domain.model.view.RoomSearchViewResponse;

@Service
public class RoomSearchProcessor {

	@Autowired
	private RoonSearchRequestConverter roonSearchRequestConverter;

	@Autowired
	private RoonSearchResponseConverter roonSearchResponseConverter;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private RoomsReservedService roomsReservedService;
	
	@Autowired
	private RoomService roomService;

	public RoomSearchViewResponse process(RoomSearchViewRequest roomSearchViewRequest) {

//		// Convert View request into Database request
//		Reservation reservation = roonSearchRequestConverter
//				.viewToDatabaseReObjectConverter(deleteReservationRequest);

		// Create Reservation through JPA repository layer
		List<Room> roomlist=getRoomDetails(roomSearchViewRequest);

		// Convert Database response into view response
		RoomSearchViewResponse roomSearchViewResponse = roonSearchResponseConverter
				.databaseToViewResObjectConverter(roomlist);

		return roomSearchViewResponse;
	}

	public List<Room> getRoomDetails(RoomSearchViewRequest roomSearchViewRequest) {
		List<Room> roomlist=new ArrayList<>();
		
		// Get ReservationList filtering formDt & toDt
		List<Object> reservationlist = reservationService.getlistFindByAnotherField(roomSearchViewRequest.getFromDt(),
				roomSearchViewRequest.getToDt());
		if (reservationlist.size() > 0) {
			reservationlist.forEach((Object resv) -> {
				Reservation reservation = (Reservation) resv;
				
				List<Object> roomResList = roomsReservedService
						.getlistFindByAnotherField(String.valueOf(reservation.getReservation_id()));
				if(roomResList.size()>0) {
					roomResList.forEach((Object obj)->{
						RoomsReserved roomsReserved = (RoomsReserved) obj;
						
						String roomNumber=roomsReserved.getRooms_booked();
						roomlist.add(roomService.onView(roomNumber));
					});
				}
				;
			});
		}
		return roomlist;
	}

}
