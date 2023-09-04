package org.java.caribecasa_elite.repository;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAll();
    List<Reservation> findAllByCustomer(Customer customer);
    // In ReservationRepository interface
    @Query("SELECT r FROM Reservation r WHERE (r.startDate <= :endDate AND r.endDate >= :startDate)")
    List<Reservation> findOverlappingReservations(LocalDate startDate, LocalDate endDate);

}