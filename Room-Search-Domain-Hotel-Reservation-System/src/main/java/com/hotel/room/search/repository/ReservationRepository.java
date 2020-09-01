package com.hotel.room.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.room.reservation.model.database.Reservation;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Integer> {

}
