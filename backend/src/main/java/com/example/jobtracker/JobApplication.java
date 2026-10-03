package com.example.jobtracker;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity //@Entity tells JPA "this class is a database table"
        //Spring creates a job_application table from it.
public class JobApplication {

    @Id // marks id as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //(@GeneratedValue(IDENTITY) => MySQL auto-increments it i.e) frst row gets 1 sec row gets 2 etc
    private Long id;

    // all these feilds below become a column
    private String company;
    private String role;
    private String status;
    private LocalDate appliedDate;
    private String notes;

    //getters and setters let Spring and JSON conversion read and write the fields
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getAppliedDate() { return appliedDate; }
    public void setAppliedDate(LocalDate appliedDate) { this.appliedDate = appliedDate; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}