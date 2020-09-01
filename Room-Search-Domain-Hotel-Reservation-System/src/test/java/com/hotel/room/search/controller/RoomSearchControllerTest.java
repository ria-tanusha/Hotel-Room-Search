package com.hotel.room.search.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.room.reservation.model.view.RoomSearchViewRequest;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;
import com.hotel.room.search.processor.RoomSearchProcessor;

@ExtendWith(MockitoExtension.class)
public class RoomSearchControllerTest {
	
	@InjectMocks
	private RoomSearchController roomSearchController;
	
	@Mock
	private RoomSearchProcessor roomSearchProcessor;
	
	@Test
	public  void serviceStateTest() {
		roomSearchController.serviceState();
	}
	
	@Test
	public  void searchRoomTest() {
		RoomSearchViewRequest roomSearchViewRequest=new RoomSearchViewRequest();
		RoomSearchViewResponse res=new RoomSearchViewResponse();
		when(roomSearchProcessor.process(any())).thenReturn(res);
		
		roomSearchController.searchRoom(roomSearchViewRequest);
	}

}
