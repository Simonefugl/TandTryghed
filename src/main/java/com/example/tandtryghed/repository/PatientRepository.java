package com.example.tandtryghed.repository;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.Employee;
import com.example.tandtryghed.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
