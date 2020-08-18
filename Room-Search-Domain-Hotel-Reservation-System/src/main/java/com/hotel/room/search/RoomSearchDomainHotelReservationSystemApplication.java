package com.hotel.room.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan({"com.room.reservation.domain.model.*"})
@SpringBootApplication()
public class RoomSearchDomainHotelReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomSearchDomainHotelReservationSystemApplication.class, args);
	}

}
