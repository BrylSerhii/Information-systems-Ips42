package com.example.admission.model;

import jakarta.persistence.*; // Якщо не працює, спробуйте javax.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "exam_scores")
public class ExamScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subjectName;
    private Double score;

    // Зв'язок з заявкою (Багато балів -> Одна заявка)
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    // --- КОНСТРУКТОРИ ---

    public ExamScore() {
    }

    public ExamScore(String subjectName, Double score) {
        this.subjectName = subjectName;
        this.score = score;
    }

    // --- ГЕТТЕРИ ТА СЕТТЕРИ ---

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    @JsonIgnore
    public Applicant getApplicant() {
        return applicant;
    }
}