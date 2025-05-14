package com.example.tandtryghed.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Patient {

    @Id
    private String patient_id;

    private String patient_last_name;
    private String patient_email;
    private int patient_phone_number;

    @OneToMany(mappedBy = "patient")
    private List<BookingConfirmation> confirmations;

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_last_name() {
        return patient_last_name;
    }

    public void setPatient_last_name(String patient_last_name) {
        this.patient_last_name = patient_last_name;
    }

    public String getPatient_email() {
        return patient_email;
    }

    public void setPatient_email(String patient_email) {
        this.patient_email = patient_email;
    }

    public int getPatient_phone_number() {
        return patient_phone_number;
    }

    public void setPatient_phone_number(int patient_phone_number) {
        this.patient_phone_number = patient_phone_number;
    }

    public List<BookingConfirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<BookingConfirmation> confirmations) {
        this.confirmations = confirmations;
    }
}
