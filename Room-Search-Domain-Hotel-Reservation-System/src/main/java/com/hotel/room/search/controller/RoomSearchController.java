package com.hotel.room.search.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.room.reservation.model.view.Response;
import com.hotel.room.reservation.model.view.RoomSearchViewRequest;
import com.hotel.room.reservation.model.view.RoomSearchViewResponse;
import com.hotel.room.search.processor.RoomSearchProcessor;

@RestController
//@RequestMapping("/Search")
public class RoomSearchController {

	@Autowired
	private RoomSearchProcessor roomSearchProcessor;

	@GetMapping(path = "/ping")
	@ResponseBody
	public String serviceState() {
		return "Room-Search-Domain-Hotel-Reservation-System  is up & running....." + LocalDateTime.now().toString();
	}

	@RequestMapping(value = "/search/room", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<Response<RoomSearchViewResponse>> searchRoom(
			@Valid @RequestBody RoomSearchViewRequest roomSearchViewRequest) {

		Response response = new Response();
		response.setData(roomSearchProcessor.process(roomSearchViewRequest));
		response.setMessage("Success Transaction");
		response.setSuccess(true);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
