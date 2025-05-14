package com.example.tandtryghed.controller;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.Employee;
import com.example.tandtryghed.model.Treatment;
import com.example.tandtryghed.repository.BookingConfirmationRepository;
import com.example.tandtryghed.repository.EmployeeRepository;
import com.example.tandtryghed.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BookingConfirmationRepository bookingRepository;

    @Autowired
    private TreatmentRepository treatmentRepository;

    // ✅ Find én ledig og autoriseret medarbejder til en bestemt dato, tid og behandling
    @GetMapping("/available")
    public ResponseEntity<Employee> findAvailableEmployee(
            @RequestParam String date,
            @RequestParam String time,
            @RequestParam String treatmentName
    ) {
        List<Employee> allEmployees = employeeRepository.findAll();
        List<BookingConfirmation> bookings = bookingRepository.findAll();

        for (Employee employee : allEmployees) {
            boolean isAuthorized = employee.getTreatments().stream()
                    .anyMatch(t -> t.getTreatment_name().equalsIgnoreCase(treatmentName));

            boolean isBusy = bookings.stream().anyMatch(b ->
                    b.getEmployee().getEmployee_id() == employee.getEmployee_id()
                            && b.getDate_of_consultation().equals(date)
                            && b.getTime_of_consultation().equals(time));

            if (isAuthorized && !isBusy) {
                return ResponseEntity.ok(employee);
            }
        }

        return ResponseEntity.status(HttpStatus
