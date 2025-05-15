package com.example.tandtryghed.controller;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.Employee;
import com.example.tandtryghed.model.Treatment;
import com.example.tandtryghed.repository.BookingConfirmationRepository;
import com.example.tandtryghed.repository.EmployeeRepository;
import com.example.tandtryghed.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/times")
public class AvailableTimesController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingConfirmationRepository bookingRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    @GetMapping("/available")
    public ResponseEntity<List<String>> getAvailableTimes(
            @RequestParam String date,
            @RequestParam String treatmentName
    ) {
        List<Employee> employees = employeeRepository.findAll();
        List<BookingConfirmation> bookings = bookingRepository.findAll();
        Treatment treatment = treatmentRepository.findAll().stream()
                .filter(t -> t.getTreatment_name().equalsIgnoreCase(treatmentName))
                .findFirst()
                .orElse(null);

        if (treatment == null) return ResponseEntity.badRequest().build();

        List<String> availableTimes = new ArrayList<>();
        List<String> allTimes = Arrays.asList("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00");

        for (String time : allTimes) {
            boolean timeIsAvailable = employees.stream()
                    .anyMatch(emp -> emp.getTreatments().stream()
                            .anyMatch(t -> t.getTreatment_name().equalsIgnoreCase(treatmentName)) &&
                            bookings.stream().noneMatch(b ->
                                    b.getEmployee().getEmployee_id() == emp.getEmployee_id() &&
                                            b.getDate_of_consultation().equals(date) &&
                                            b.getTime_of_consultation().equals(time)));

            if (timeIsAvailable) {
                availableTimes.add(time);
            }
        }

        return ResponseEntity.ok(availableTimes);
    }
}
