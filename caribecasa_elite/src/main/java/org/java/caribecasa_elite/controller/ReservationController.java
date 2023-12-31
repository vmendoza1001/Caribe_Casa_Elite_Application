package org.java.caribecasa_elite.controller;

import org.java.caribecasa_elite.model.Reservation;
import org.java.caribecasa_elite.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    // Display all reservations for the logged-in customer
    @GetMapping("/all")
    public String listReservations(Model model, Principal principal) {
        List<Reservation> reservations = reservationService.findAllByCustomer(principal.getName());
        model.addAttribute("reservations", reservations);
        return "reservations-list";
    }

    // Form for new reservation
    @GetMapping("/new")
    public String newReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation-form";
    }

    // Save the new reservation
    @PostMapping("/save")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation, Principal principal) {
        reservationService.createOrUpdateReservation(reservation, principal.getName());
        return "redirect:/reservation/all";
    }


    // Edit an existing reservation
    @GetMapping("/edit/{id}")
    public String editReservation(@PathVariable Long id, Model model) {
        Optional<Reservation> optionalReservation = reservationService.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            model.addAttribute("reservation", reservation);
            return "reservation-form";
        } else {
            // Handle the case where the reservation is not found, e.g., redirect to an error page.
            return "error-page";
        }
    }


    // Delete a reservation
    @GetMapping("/delete/{id}")
    public String deleteReservation(@PathVariable Long id) {
        reservationService.deleteById(id);
        return "redirect:/reservation/all";
    }
}
