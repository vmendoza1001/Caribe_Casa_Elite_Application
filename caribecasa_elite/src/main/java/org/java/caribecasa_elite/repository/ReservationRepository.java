package org.java.caribecasa_elite.repository;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();
    List<Reservation> findAllByCustomer(Customer customer);
}