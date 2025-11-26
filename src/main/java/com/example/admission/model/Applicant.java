package com.example.admission.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applicants")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String facultyName;
    private Double totalScore;

    private LocalDateTime registeredAt;

    public Applicant() {}

    public Applicant(String fullName, String facultyName, Double totalScore) {
        this.fullName = fullName;
        this.facultyName = facultyName;
        this.totalScore = totalScore;
        this.registeredAt = LocalDateTime.now();
    }

    // Getters are required for the API to return JSON
    public String getFullName() { return fullName; }
    public String getFacultyName() { return facultyName; }
    public Double getTotalScore() { return totalScore; }
}