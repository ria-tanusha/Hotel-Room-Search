package com.hotel.room.search.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.room.search.processor.RoomSearchProcessor;
import com.room.reservation.domain.model.view.RoomSearchViewRequest;
import com.room.reservation.domain.model.view.RoomSearchViewResponse;

@RestController
@RequestMapping("/Search")
public class RoomSearchController {

	@Autowired
	private RoomSearchProcessor roomSearchProcessor;

	@GetMapping(path = "/ping")
	@ResponseBody
	public String serviceState() {
		return "Room-Search-Domain-Hotel-Reservation-System  is up & running....." + LocalDateTime.now().toString();
	}

	@RequestMapping(value = "/Room/{fromDt}/{toDt}/{roomType}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	RoomSearchViewResponse viewReservation(@PathVariable("fromDt") String fromDt, @PathVariable("toDt") String toDt,
			@PathVariable("roomType") String roomType) {
		
		RoomSearchViewRequest roomSearchViewRequest = new RoomSearchViewRequest();
		roomSearchViewRequest.setFromDt(fromDt);
		roomSearchViewRequest.setToDt(toDt);
		roomSearchViewRequest.setRoomType(roomType);
		return roomSearchProcessor.process(roomSearchViewRequest);
	}

}
