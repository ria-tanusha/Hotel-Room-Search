package com.hotel.room.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotel.room.reservation.model.database.RoomsReserved;

@Repository
public interface RoomsReservedRepository extends JpaRepository<RoomsReserved, Integer> {

	@Query("Select rr from RoomsReserved rr where  rr.room_type= :roomType AND rr.reserved_date= :reservedDate AND rr.active=1")
	public List<RoomsReserved> getListFindByRoomTypeAndReservedDate(@Param("roomType") String roomType,
			@Param("reservedDate") String reservedDate);

	@Query("Select rr from RoomsReserved rr where rr.reserved_date= :reservedDate AND rr.active=1")
	public List<RoomsReserved> getListFindByReservedDate(@Param("reservedDate") String reservedDate);

}
