package com.example.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    // --- FIX IS HERE: Changed (ExamScore) to <ExamScore> ---
    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ExamScore> scores = new ArrayList<>();

    private Double totalScore = 0.0;
    private String status = "REGISTERED"; // REGISTERED, ADMITTED, REJECTED

    public Applicant() {}

    public Applicant(String fullName, Faculty faculty) {
        this.fullName = fullName;
        this.faculty = faculty;
    }

    public void addScore(ExamScore score) {
        scores.add(score);
        score.setApplicant(this);
        recalculateTotal();
    }

    public void recalculateTotal() {
        this.totalScore = scores.stream().mapToDouble(ExamScore::getScore).sum();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public Faculty getFaculty() { return faculty; }
    public Double getTotalScore() { return totalScore; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<ExamScore> getScores() { return scores; }
}