package com.example.tandtryghed.repository;

import com.example.tandtryghed.model.Employee;
import com.example.tandtryghed.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}
