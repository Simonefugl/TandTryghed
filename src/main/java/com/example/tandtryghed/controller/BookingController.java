package com.example.tandtryghed.controller;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.BookingRequest;
import com.example.tandtryghed.model.Patient;
import com.example.tandtryghed.repository.BookingConfirmationRepository;
import com.example.tandtryghed.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
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
    public BookingConfirmation createBooking(@RequestBody BookingRequest request) {
        System.out.println("received createBooking()");
        // Find eksisterende patient via email
        System.out.println("finding patient by email " + request.email);
        Patient patient = patientRepository.findByPatientEmail(request.email)
                .orElseGet(() -> {
                    System.out.println("making new patient");
                    Patient nyPatient = new Patient();
                    nyPatient.setPatient_first_name(request.firstName);
                    nyPatient.setPatient_last_name(request.lastName);
                    nyPatient.setPatient_email(request.email);
                    nyPatient.setPatient_phone_number(request.phone);
                    System.out.println("saving patient");
                    return patientRepository.save(nyPatient);
                });

        BookingConfirmation booking = new BookingConfirmation();
        booking.setTreatment_name(request.treatmentName);
        System.out.println("setting date of consultation");
        booking.setDate_of_consultation(request.date); // Husk at bruge LocalDate hvis muligt!
        System.out.println("set the date");
        booking.setTime_of_consultation(request.time);
        booking.setPatient(patient);

        return bookingRepository.save(booking);
    }


}
