// ✅ Fuld EmployeeController.java med nyt endpoint til at finde gyldige datoer for en behandling

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

import java.time.DayOfWeek;
import java.time.LocalDate;
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

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    /* finder alle mulige datoer inden for de næste 14 dage (kun hverdage),
     hvor mindst én medarbejder er autoriseret til at udføre en bestemt behandling
     og ikke er booket hele dagen. */
    @GetMapping("/available-dates")
    public ResponseEntity<List<String>> getAvailableDates(
            @RequestParam String treatmentName
    ) {
        List<Employee> employees = employeeRepository.findAll();
        List<BookingConfirmation> bookings = bookingRepository.findAll();
        List<Treatment> treatments = treatmentRepository.findAll();

        //Henter alle medarbejdere, bookinger og behandlinger fra databasen.
        Treatment treatment = treatments.stream()
                .filter(t -> t.getTreatment_name().equalsIgnoreCase(treatmentName))
                .findFirst()
                .orElse(null);

        if (treatment == null) return ResponseEntity.badRequest().build();

        int duration = treatment.getDuration_minutes();
        Set<String> validDates = new HashSet<>();


        LocalDate today = LocalDate.now();
        for (int i = 0; i < 14; i++) { // Laver en ny dato, der er i dage fra i dag (0 til 13)
            LocalDate date = today.plusDays(i);
            // Hvis det er lørdag eller søndag, springes denne iteration over
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) continue;

            for (Employee employee : employees) {
                boolean isAuthorized = employee.getTreatments().stream()
                        .anyMatch(t -> t.getTreatment_name().equalsIgnoreCase(treatmentName));

                if (!isAuthorized) continue;

                for (int hour = 9; hour <= 16; hour++) {
                    String time = hour + ":00";
                    boolean isBusy = bookings.stream().anyMatch(b ->
                            b.getEmployee().getEmployee_id() == employee.getEmployee_id()
                                    && b.getDate_of_consultation().equals(date.toString())
                                    && b.getTime_of_consultation().equals(time)
                    );
                    if (!isBusy) {
                        validDates.add(date.toString());
                        break;
                    }
                }
            }
        }

        List<String> validDatesString = new ArrayList<>(validDates);
        Collections.sort(validDatesString);
        return ResponseEntity.ok(validDatesString);
    }
}