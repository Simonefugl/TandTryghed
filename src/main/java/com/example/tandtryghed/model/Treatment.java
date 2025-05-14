package com.example.tandtryghed.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int treatment_id;

    private String treatment_name;

    private int duration_minutes;


    //Denne ManyToMany-relation forbinder Treatment med Employee.
    //Hibernate opretter automatisk en mellemledstabel kaldet "employee_treatments"
    //med kolonnerne "treatment_id" og "employee_id".
    @ManyToMany(mappedBy = "treatments")
    private List<Employee> employees;

    @OneToMany(mappedBy = "treatment")
    private List<BookingConfirmation> confirmations;

    // Getters og setters

    public int getTreatment_id() {
        return this.treatment_id;
    }

    public void setTreatment_id(int treatment_id) {
        this.treatment_id = treatment_id;
    }

    public String getTreatment_name() {
        return this.treatment_name;
    }

    public void setTreatment_name(String treatment_name) {
        this.treatment_name = treatment_name;
    }

    public List<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<BookingConfirmation> getConfirmations() {
        return this.confirmations;
    }

    public void setConfirmations(List<BookingConfirmation> confirmations) {
        this.confirmations = confirmations;
    }

    public int getDuration_minutes() {
        return duration_minutes;
    }

    public void setDuration_minutes(int duration_minutes) {
        this.duration_minutes = duration_minutes;
    }

}
