package org.java.caribecasa_elite.service;

import org.java.caribecasa_elite.model.Customer;
import org.java.caribecasa_elite.model.Reservation;
import org.java.caribecasa_elite.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerService customerService;
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


    public Reservation createOrUpdateReservation(Reservation reservation, String username) {
        Customer customer = customerService.findByUsername(username);

        if (customer == null) {
            throw new RuntimeException("User not found!");
        }

        // Associate the reservation with the customer
        reservation.setCustomer(customer);

        // Business logic to check if the apartment is available for these dates
        if (!isApartmentAvailable(reservation.getStartDate(), reservation.getEndDate())) {
            throw new RuntimeException("Apartment is not available for these dates!");
        }

        // Save the reservation
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> findAllByCustomer(String username) {
        // Get the customer using the username
        Customer customer = customerService.findByUsername(username);

        if(customer != null) {
            return reservationRepository.findAllByCustomer(customer);
        }

        return new ArrayList<>();  // Return an empty list if no customer is found
    }

    public void deleteById(Long id) {
        reservationRepository.deleteById(id);
    }

}
