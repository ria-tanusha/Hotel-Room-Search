package com.hotel.room.search.roomSearchConverter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.room.reservation.domain.model.database.Room;
import com.room.reservation.domain.model.view.RoomSearchViewResponse;
import com.room.reservation.domain.model.view.RoomView;
import com.room.reservation.domain.model.view.StatusView;

@Service
public class RoonSearchResponseConverter {
	
	public RoomSearchViewResponse databaseToViewResObjectConverter(List<Room> roomlist) {

		RoomSearchViewResponse roomSearchViewResponse = new RoomSearchViewResponse();
		List<RoomView> roomViewList=new ArrayList<>();
		
		if(roomlist.size()>0) {
			roomlist.forEach((Room room)->{
				RoomView   roomView=new RoomView();
				roomView.setRoomFeatures(room.getRoom_features());
				roomView.setRoomNumber(room.getRoom_number());
				roomView.setRoomRate(room.getRoom_rate());
				roomView.setRoomType(room.getRoom_type());
				roomViewList.add(roomView);
			});
		}
		roomSearchViewResponse.setRoomDetails(roomViewList);

		// Prepare Status
		StatusView status = new StatusView();
		status.setMessage("SUCCESS");
		status.setStatusCode("200 OK status code");
		roomSearchViewResponse.setStatus(status);
		
		return roomSearchViewResponse;
	}

}
