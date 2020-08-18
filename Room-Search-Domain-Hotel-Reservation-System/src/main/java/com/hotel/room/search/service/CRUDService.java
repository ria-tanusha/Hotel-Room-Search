package com.hotel.room.search.service;

import java.util.List;

import com.room.reservation.domain.model.database.Reservation;

public interface CRUDService {

	 public void onCreate(Object obj);
	 
	 public Object onView(String str);
	 
	 public void onUpdate();
	 
	 public void onDelete();
	 
	 public List<Object> getlistFindByAnotherField(String... str);


	 
	 
}
