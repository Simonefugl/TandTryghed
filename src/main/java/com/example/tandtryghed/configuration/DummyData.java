package com.example.tandtryghed.configuration;

import com.example.tandtryghed.model.*;
import com.example.tandtryghed.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

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

            if (employeeRepo.count() > 0) return; // Undgå at indsætte to gange

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

            // 🔹 Medarbejdere - 3 tandlæger + 3 klinikassistenter
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
            maria.setEmployee_last_name("Jespersen");
            maria.setEmployee_role("Tandlæge");
            maria.setTreatments(List.of(tjek, rodbehandling));

            Employee line = new Employee();
            line.setEmployee_first_name("Line");
            line.setEmployee_last_name("Thomsen");
            line.setEmployee_role("Klinikassistent");
            line.setTreatments(List.of(tandrens));

            Employee frederik = new Employee();
            frederik.setEmployee_first_name("Frederik");
            frederik.setEmployee_last_name("Skov");
            frederik.setEmployee_role("Klinikassistent");
            frederik.setTreatments(List.of(tjek));

            Employee sofia = new Employee();
            sofia.setEmployee_first_name("Sofia");
            sofia.setEmployee_last_name("Østergaard");
            sofia.setEmployee_role("Klinikassistent");
            sofia.setTreatments(List.of(tandrens, tjek));

            employeeRepo.saveAll(List.of(emma, kasper, maria, line, frederik, sofia));

            // 🔹 Patienter
            Patient sofie = new Patient();
            sofie.setPatient_id("SF123");
            sofie.setPatient_last_name("Nielsen");
            sofie.setPatient_email("sofie@example.com");
            sofie.setPatient_phone_number(20405060);

            Patient mads = new Patient();
            mads.setPatient_id("MD456");
            mads.setPatient_last_name("Jensen");
            mads.setPatient_email("mads@example.com");
            mads.setPatient_phone_number(50102030);

            Patient laila = new Patient();
            laila.setPatient_id("LB789");
            laila.setPatient_last_name("Berg");
            laila.setPatient_email("laila@example.com");
            laila.setPatient_phone_number(30201040);

            patientRepo.saveAll(List.of(sofie, mads, laila));

            // 🔹 Bookinger
            BookingConfirmation booking1 = new BookingConfirmation();
            booking1.setDate_of_consultation("2025-05-14");
            booking1.setTime_of_consultation("10:00");
            booking1.setEmployee(emma);
            booking1.setTreatment(rodbehandling);
            booking1.setPatient(sofie);

            BookingConfirmation booking2 = new BookingConfirmation();
            booking2.setDate_of_consultation("2025-06-02");
            booking2.setTime_of_consultation("13:00");
            booking2.setEmployee(kasper);
            booking2.setTreatment(tjek);
            booking2.setPatient(mads);

            BookingConfirmation booking3 = new BookingConfirmation();
            booking3.setDate_of_consultation("2025-05-15");
            booking3.setTime_of_consultation("08:30");
            booking3.setEmployee(maria);
            booking3.setTreatment(rodbehandling);
            booking3.setPatient(laila);

            BookingConfirmation booking4 = new BookingConfirmation();
            booking4.setDate_of_consultation("2025-05-15");
            booking4.setTime_of_consultation("09:00");
            booking4.setEmployee(emma);
            booking4.setTreatment(tandrens);
            booking4.setPatient(mads);

            bookingRepo.saveAll(List.of(booking1, booking2, booking3, booking4));

            System.out.println("✅ Dummy data indsat!");
        };
    }
}
