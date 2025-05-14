package com.example.tandtryghed.controller;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.repository.BookingConfirmationRepository;
import com.example.tandtryghed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingConfirmationRepository bookingRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public List<BookingConfirmation> getAllBookings() {
        return this.bookingRepository.findAll(); // returnerer JSON
    }

    @PostMapping
    public BookingConfirmation createBooking(@RequestBody BookingConfirmation booking) {
        // Hvis patienten ikke findes i DB (fx via patient_id), så gem den først
        this.patientRepository.save(booking.getPatient());

        return this.bookingRepository.save(booking);
    }

}
