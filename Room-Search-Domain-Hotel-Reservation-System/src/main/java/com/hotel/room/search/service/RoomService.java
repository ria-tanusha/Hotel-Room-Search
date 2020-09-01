package com.hotel.room.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hotel.room.reservation.model.database.Room;
import com.hotel.room.search.exceptionhandling.RoomTypeNotFoundException;
import com.hotel.room.search.repository.RoomRepository;


@Service
public class RoomService{
	
	@Autowired
	RoomRepository roomRepository;

	public List<Room> findAll(){
		return roomRepository.findAll();
	}

	public List<Room> getlistFindByRoomtype(String str) {
		List<Room> roomList=roomRepository.getListFindByRoomType(str);
		if(CollectionUtils.isEmpty(roomList)) {
			throw new RoomTypeNotFoundException(str+" room type is not found");
		}
		return roomList;
	}

}
