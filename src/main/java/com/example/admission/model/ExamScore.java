package com.example.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ExamScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName; // Math, Physics, History
    private Double score;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    @JsonIgnore // Prevent infinite JSON loops
    private Applicant applicant;

    public ExamScore() {}

    public ExamScore(String subjectName, Double score) {
        this.subjectName = subjectName;
        this.score = score;
    }

    public void setApplicant(Applicant applicant) { this.applicant = applicant; }
    public Double getScore() { return score; }
    public String getSubjectName() { return subjectName; }
}