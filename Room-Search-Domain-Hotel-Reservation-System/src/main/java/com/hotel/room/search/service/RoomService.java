package com.hotel.room.search.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.room.search.repository.RoomRepository;
import com.room.reservation.domain.model.database.Room;


@Service
public class RoomService implements CRUDService{
	
	@Autowired
	RoomRepository roomRepository;

	@Override
	public void onCreate(Object obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Room onView(String id) {
		Optional<Room> reservationOpt=roomRepository.findById(Integer.parseInt(id));
		if(reservationOpt.isPresent()) {
			 return reservationOpt.get();
		}
		return new Room();
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object> getlistFindByAnotherField(String... str) {
		// TODO Auto-generated method stub
		return null;
	}

}
