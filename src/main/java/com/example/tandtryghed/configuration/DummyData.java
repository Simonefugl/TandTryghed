package com.example.tandtryghed.configuration;

import com.example.tandtryghed.model.*;
import com.example.tandtryghed.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Arrays;

@Configuration
public class DummyData {

    @Bean
    CommandLineRunner initData(
            EmployeeRepository employeeRepo,
            TreatmentRepository treatmentRepo,
            PatientRepository patientRepo,
            BookingConfirmationRepository bookingRepo
    ) {
        return args -> {
            System.out.println("🦷 Indsætter dummy data til tandplejesystem...");

            if (employeeRepo.count() > 0) return;

            // 🔹 Behandlinger
            Treatment rodbehandling = new Treatment();
            rodbehandling.setTreatment_name("Rodbehandling");
            rodbehandling.setDuration_minutes(45);

            Treatment tandrens = new Treatment();
            tandrens.setTreatment_name("Tandrensning");
            tandrens.setDuration_minutes(20);

            Treatment tjek = new Treatment();
            tjek.setTreatment_name("Regelmæssigt tjek");
            tjek.setDuration_minutes(30);

            treatmentRepo.saveAll(List.of(rodbehandling, tandrens, tjek));

            // 🔹 Medarbejdere
            Employee emma = new Employee();
            emma.setEmployee_first_name("Emma");
            emma.setEmployee_last_name("Hansen");
            emma.setEmployee_role("Tandlæge");
            emma.setTreatments(List.of(rodbehandling, tandrens));

            Employee kasper = new Employee();
            kasper.setEmployee_first_name("Kasper");
            kasper.setEmployee_last_name("Larsen");
            kasper.setEmployee_role("Tandlæge");
            kasper.setTreatments(List.of(tandrens, tjek));

            Employee maria = new Employee();
            maria.setEmployee_first_name("Maria");
            maria.setEmployee_last_name("Nielsen");
            maria.setEmployee_role("Tandlæge");
            maria.setTreatments(List.of(rodbehandling, tjek));

            Employee ida = new Employee();
            ida.setEmployee_first_name("Ida");
            ida.setEmployee_last_name("Madsen");
            ida.setEmployee_role("Klinikassistent");
            ida.setTreatments(List.of(tandrens));

            Employee jonas = new Employee();
            jonas.setEmployee_first_name("Jonas");
            jonas.setEmployee_last_name("Pedersen");
            jonas.setEmployee_role("Klinikassistent");
            jonas.setTreatments(List.of(tandrens, tjek));

            Employee sofie = new Employee();
            sofie.setEmployee_first_name("Sofie");
            sofie.setEmployee_last_name("Jørgensen");
            sofie.setEmployee_role("Klinikassistent");
            sofie.setTreatments(List.of(rodbehandling));

            employeeRepo.saveAll(List.of(emma, kasper, maria, ida, jonas, sofie));

            // 🔹 Patienter
            Patient patient1 = new Patient();
            patient1.setPatient_first_name("Lise");
            patient1.setPatient_last_name("Mortensen");
            patient1.setPatient_email("lise@mail.com");
            patient1.setPatient_phone_number("12345678");

            Patient patient2 = new Patient();
            patient2.setPatient_first_name("Mikkel");
            patient2.setPatient_last_name("Andersen");
            patient2.setPatient_email("mikkel@mail.com");
            patient2.setPatient_phone_number("87654321");

            patientRepo.saveAll(List.of(patient1, patient2));

            // 🔹 Bookinger (nogle tider blokeret til test)
            BookingConfirmation b1 = new BookingConfirmation();
            b1.setPatient(patient1);
            b1.setEmployee(emma);
            b1.setTreatment(rodbehandling);
            b1.setDate_of_consultation("2025-05-15");
            b1.setTime_of_consultation("10:00");

            BookingConfirmation b2 = new BookingConfirmation();
            b2.setPatient(patient2);
            b2.setEmployee(kasper);
            b2.setTreatment(tandrens);
            b2.setDate_of_consultation("2025-05-15");
            b2.setTime_of_consultation("11:00");

            BookingConfirmation b3 = new BookingConfirmation();
            b3.setPatient(patient1);
            b3.setEmployee(maria);
            b3.setTreatment(tjek);
            b3.setDate_of_consultation("2025-05-16");
            b3.setTime_of_consultation("09:00");

            BookingConfirmation b4 = new BookingConfirmation();
            b4.setPatient(patient2);
            b4.setEmployee(ida);
            b4.setTreatment(tandrens);
            b4.setDate_of_consultation("2025-05-16");
            b4.setTime_of_consultation("10:00");

            BookingConfirmation b5 = new BookingConfirmation();
            b5.setPatient(patient1);
            b5.setEmployee(sofie);
            b5.setTreatment(rodbehandling);
            b5.setDate_of_consultation("2025-05-17");
            b5.setTime_of_consultation("14:00");

            bookingRepo.saveAll(List.of(b1, b2, b3, b4, b5));
        };
    }
}
