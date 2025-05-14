package com.example.tandtryghed.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;

    private String employee_first_name;
    private String employee_last_name;
    private String employee_role;

    // Mange-til-mange relation til Treatment via en mellemledstabel
    //Hibernate genererer automatisk employee_treatments med employee_id og treatment_id.
    @ManyToMany
    @JoinTable(
            //Her fortæller vi hvilken tabel skal bruges som mellemled
            name = "employee_treatments",
            //Hvilken kolonne i join-tabellen peger på denne klasse (Employee): employee_id
            joinColumns = @JoinColumn(name = "employee_id"),
            //Hvilken kolonne i join-tabellen peger på den anden klasse (Treatment): treatment_id
            inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    private List<Treatment> treatments;

    // En-til-mange relation til BookingConfirmation
    @OneToMany(mappedBy = "employee")
    private List<BookingConfirmation> confirmations;

    // Getters og setters
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_first_name() {
        return employee_first_name;
    }

    public void setEmployee_first_name(String employee_first_name) {
        this.employee_first_name = employee_first_name;
    }

    public String getEmployee_last_name() {
        return employee_last_name;
    }

    public void setEmployee_last_name(String employee_last_name) {
        this.employee_last_name = employee_last_name;
    }

    public String getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(String employee_role) {
        this.employee_role = employee_role;
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<Treatment> treatments) {
        this.treatments = treatments;
    }

    public List<BookingConfirmation> getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(List<BookingConfirmation> confirmations) {
        this.confirmations = confirmations;
    }
}

