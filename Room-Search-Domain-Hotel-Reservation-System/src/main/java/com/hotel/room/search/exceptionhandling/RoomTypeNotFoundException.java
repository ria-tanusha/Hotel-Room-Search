package com.hotel.room.search.exceptionhandling;

public class RoomTypeNotFoundException extends RuntimeException {

	String message;

	public RoomTypeNotFoundException(String message) {
		this.message = message;
	}

	public String toString() {
		return message;
	}

}
