package com.hotel.room.search.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.room.reservation.model.database.Room;
import com.hotel.room.search.exceptionhandling.RoomTypeNotFoundException;
import com.hotel.room.search.repository.RoomRepository;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

	@InjectMocks
	private RoomService roomService;

	@Mock
	private RoomRepository roomRepository;

	@Test
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getlistFindByRoomtypeTest() {
		List<Room> roomList = new ArrayList<>();
		Room room = new Room();
		room.setRoom_features("Large with 2 bed");
		room.setRoom_number(101);
		room.setRoom_rate(new BigDecimal("1000.00"));
		room.setRoom_type("3rd class");
		roomList.add(room);

		when(roomRepository.getListFindByRoomType(any())).thenReturn(roomList);
		roomService.getlistFindByRoomtype("3rd class");

		when(roomRepository.getListFindByRoomType(any())).thenReturn(null);
		try {
			roomService.getlistFindByRoomtype(null);
		} catch (RoomTypeNotFoundException ex) {
			ex.getMessage();
		}

	}
}
