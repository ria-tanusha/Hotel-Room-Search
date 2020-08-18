package com.hotel.room.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.room.reservation.domain.model.database.RoomsReserved;

@Repository
public interface RoomsReservedRepository extends JpaRepository<RoomsReserved, Integer>{
	
}
