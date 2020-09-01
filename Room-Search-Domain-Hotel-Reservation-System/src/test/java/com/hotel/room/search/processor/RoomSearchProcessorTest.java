package com.hotel.room.search.processor;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hotel.room.reservation.model.view.RoomSearchViewRequest;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomSearchProcessorTest {

	@Autowired
	private RoomSearchProcessor roomSearchProcessor;
	
	@Test
	public void processTestWithoutRoomType() {
		
		RoomSearchViewRequest roomSearchViewRequest=new RoomSearchViewRequest();
		roomSearchViewRequest.setFromDt("2020-08-19");
		roomSearchViewRequest.setToDt("2020-08-20");
		RoomSearchViewResponse roomSearchViewResponse=roomSearchProcessor.process(roomSearchViewRequest);
		assertNotNull(roomSearchViewResponse);
		
	}
	
	@Test
	public void processTestWithRoomType() {
		
		RoomSearchViewRequest roomSearchViewRequest=new RoomSearchViewRequest();
		roomSearchViewRequest.setFromDt("2020-08-19");
		roomSearchViewRequest.setToDt("2020-08-20");
		roomSearchViewRequest.setRoomType("1st class");
		RoomSearchViewResponse roomSearchViewResponse=roomSearchProcessor.process(roomSearchViewRequest);
		assertNotNull(roomSearchViewResponse);
		
	}
}
