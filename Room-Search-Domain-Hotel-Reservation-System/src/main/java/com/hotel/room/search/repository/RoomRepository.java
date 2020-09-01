package com.hotel.room.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.room.reservation.model.database.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {

	@Query("Select r from Room r where r.room_type= :roomType")
	public List<Room> getListFindByRoomType(@Param("roomType") String roomType);

}
