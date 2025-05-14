package com.example.tandtryghed.repository;


import com.example.tandtryghed.model.BookingConfirmation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface BookingConfirmationRepository extends JpaRepository<BookingConfirmation, Integer> {
}
