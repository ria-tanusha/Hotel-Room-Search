package com.hotel.room.search.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hotel.room.search.repository.ReservationRepository;
import com.room.reservation.domain.model.database.Reservation;

@Transactional
@Service
public class ReservationService implements CRUDService {
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ReservationRepository reservationRepository;

	@Override
	public void onCreate(Object obj) {
		reservationRepository.save(Reservation.class.cast(obj));
	}

	@Override
	public Reservation onView(String identifier_number) {
		Optional<Reservation> reservationOpt = reservationRepository.findById(Integer.parseInt(identifier_number));
		if (reservationOpt.isPresent()) {
			return reservationOpt.get();
		}
		return new Reservation();
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public void onDelete() {
	}

	@Override
	public List<Object> getlistFindByAnotherField(String... str) {
		Query query = entityManager
		.createQuery("Select * from Reservation r where r.check_in_date && r.check_out_date= " + str[0]+str[1]);

		return query.getResultList();
	}

}
