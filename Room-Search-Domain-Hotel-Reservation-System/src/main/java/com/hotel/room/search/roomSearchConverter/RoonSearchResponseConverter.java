package com.hotel.room.search.roomSearchConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.hotel.room.reservation.model.view.PerDayViewRoomDetails;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;
import com.hotel.room.reservation.model.view.RoomView;
import com.hotel.room.reservation.model.view.StatusView;

@Service
public class RoonSearchResponseConverter {
	
	public RoomSearchViewResponse databaseToViewResObjectConverter(Map<String,Map<String,Integer>> roomMap) {

		RoomSearchViewResponse roomSearchViewResponse = new RoomSearchViewResponse();
		List<PerDayViewRoomDetails> perDayViewrooms=new ArrayList<>();
		
		//Per day room availabilty
		for(String eachDate:roomMap.keySet()) {
			Map<String,Integer> roomTypeMap=roomMap.get(eachDate);
			List<RoomView> roomViewList=new ArrayList<>();
			int cnt=0;
			
			AtomicInteger index=new AtomicInteger();
			if(Objects.nonNull(roomTypeMap) && roomTypeMap.size()>0) {
				for(String type:roomTypeMap.keySet()) {
					
					RoomView roomView=new RoomView();
					roomView.setSlNo(index.getAndIncrement()+1);
					roomView.setRoomCount(roomTypeMap.get(type));
					roomView.setRoomType(type);
					roomView.setMsg(roomTypeMap.get(type)+"  rooms are available at "+eachDate);
					roomViewList.add(roomView);
					cnt=cnt+roomTypeMap.get(type);
					
				}
			}
			PerDayViewRoomDetails perDayViewRoomDetails=new PerDayViewRoomDetails();
			perDayViewRoomDetails.setDate(eachDate);
			perDayViewRoomDetails.setMsg("Total "+cnt+"  rooms are available at "+eachDate);
			perDayViewRoomDetails.setRooms(roomViewList);
			perDayViewrooms.add(perDayViewRoomDetails);
			
		}
		roomSearchViewResponse.setPerDayViewRooms(perDayViewrooms);
		

		// Prepare Status
		StatusView status = new StatusView();
		status.setMessage("Retrieve Room details");
		status.setStatusCode("SUCCESS with 200 status code");
		roomSearchViewResponse.setStatus(status);
		
		return roomSearchViewResponse;
	}

}
