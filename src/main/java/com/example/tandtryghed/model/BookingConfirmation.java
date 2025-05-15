package com.example.tandtryghed.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class BookingConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int confirmation_id;

    private LocalDate date_of_consultation;
    private LocalDate time_of_consultation;


    private String treatment_name;

    // Mange-til-en: Én booking har én medarbejder
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    // Mange-til-en: Én booking har én behandling
    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    // Mange-til-en: Én booking har én patient
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



    public int getConfirmation_id() {
        return confirmation_id;
    }

    public void setConfirmation_id(int confirmation_id) {
        this.confirmation_id = confirmation_id;
    }

    public LocalDate getDate_of_consultation() {
        return date_of_consultation;
    }

    public void setDate_of_consultation(LocalDate date_of_consultation) {
        this.date_of_consultation = date_of_consultation;
    }


    public LocalDate getTime_of_consultation() {
        return time_of_consultation;
    }

    public void setTime_of_consultation(LocalDate time_of_consultation) {
        this.time_of_consultation = time_of_consultation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTreatment_name() {
        return treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        this.treatment_name = treatment_name;
    }
}