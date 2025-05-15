package com.example.tandtryghed.repository;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.Employee;
import com.example.tandtryghed.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    //Optional<Patient> findByPatientEmail(String email);
    @Query("SELECT p FROM Patient p WHERE p.patient_email = :email")
    Optional<Patient> findByPatientEmail(@Param("email") String email);
}

