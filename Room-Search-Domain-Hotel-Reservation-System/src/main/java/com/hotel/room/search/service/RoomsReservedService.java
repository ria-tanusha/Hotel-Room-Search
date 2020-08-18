package com.hotel.room.search.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.room.search.repository.RoomsReservedRepository;
import com.room.reservation.domain.model.database.Reservation;
import com.room.reservation.domain.model.database.RoomsReserved;

@Transactional
@Service
public class RoomsReservedService implements CRUDService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	RoomsReservedRepository roomReservedRepository;

	@Override
	public void onCreate(Object obj) {
	}

	@Override
	public RoomsReserved onView(String id) {
		Optional<RoomsReserved> roomsReservedOpt = roomReservedRepository.findById(Integer.parseInt(id));
		if (roomsReservedOpt.isPresent()) {
			return roomsReservedOpt.get();
		}
		return new RoomsReserved();
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
		Query query = entityManager.createQuery("Select * from RoomsReserved rr where rr.reservation.reservation_id = '"
				+ Integer.parseInt(str[0]) + "'");

		return query.getResultList();
	}

}
