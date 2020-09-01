package com.hotel.room.search.processor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.room.reservation.model.view.RoomSearchViewRequest;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;
import com.hotel.room.search.roomSearchConverter.RoonSearchRequestConverter;
import com.hotel.room.search.roomSearchConverter.RoonSearchResponseConverter;

@Service
public class RoomSearchProcessor {

	@Autowired
	private RoonSearchRequestConverter roonSearchRequestConverter;

	@Autowired
	private RoonSearchResponseConverter roonSearchResponseConverter;

	public RoomSearchViewResponse process(RoomSearchViewRequest roomSearchViewRequest) {

		//Convert View data to Entity
		 Map<String,Map<String,Integer>> roomMap=roonSearchRequestConverter.viewObjToDatabaseObjRequestConverter(roomSearchViewRequest);

		// Convert Database response into view response
		 RoomSearchViewResponse roomSearchViewResponse = roonSearchResponseConverter
				.databaseToViewResObjectConverter(roomMap);

		return roomSearchViewResponse;
	}

}
