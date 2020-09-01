package com.hotel.room.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.room.reservation.model.database.RoomsReserved;
import com.hotel.room.search.repository.RoomsReservedRepository;

@Transactional
@Service
public class RoomsReservedService {

	@Autowired
	RoomsReservedRepository roomReservedRepository;

	public List<RoomsReserved> getListFindByRoomTypeAndReservedDate(String roomType, String reservedDate) {
		return roomReservedRepository.getListFindByRoomTypeAndReservedDate(roomType, reservedDate);
	}

}
