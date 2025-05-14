package com.example.tandtryghed.repository;

import com.example.tandtryghed.model.BookingConfirmation;
import com.example.tandtryghed.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
