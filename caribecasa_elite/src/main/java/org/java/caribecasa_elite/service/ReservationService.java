package org.java.caribecasa_elite.service;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.model.Reservation;
import org.java.caribecasa_elite.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    public Reservation createOrUpdateReservation(Reservation reservation) {
        // Business logic to check if the apartment is available for these dates
        // Not implemented here for brevity

        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAllByCustomer(Customer customer) {
        return reservationRepository.findAllByCustomer(customer);
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }
    public boolean isApartmentAvailable(LocalDate startDate, LocalDate endDate) {
        List<Reservation> existingReservations = reservationRepository.findAll();

        for (Reservation existingReservation : existingReservations) {
            if (startDate.isBefore(existingReservation.getEndDate()) &&
                    endDate.isAfter(existingReservation.getStartDate())) {
                return false; // Overlapping reservation found
            }
        }

        return true; // No overlapping reservations
    }

}
