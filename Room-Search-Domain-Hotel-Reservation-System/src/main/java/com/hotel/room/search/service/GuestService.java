package com.hotel.room.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.room.search.repository.GuestRepository;
import com.room.reservation.domain.model.database.Guest;

@Service
public class GuestService implements CRUDService {
	
	@Autowired
	private GuestRepository guestRepository;

	@Override
	public void onCreate(Object obj) {
		guestRepository.save(Guest.class.cast(obj));
		
	}

	@Override
	public Guest onView(String id) {
		
		//Optional<Guest> guestOpt=guestRepository.findById(Integer.parseInt(id));
		if(guestRepository.findById(Integer.parseInt(id)).isPresent()) {
			 return guestRepository.findById(Integer.parseInt(id)).get();
		}
		return new Guest();
	}

	@Override
	public void onUpdate() {
		
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
