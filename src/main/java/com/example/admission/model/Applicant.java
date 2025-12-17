package com.example.admission.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "applicants")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    private ApplicantStatus status;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExamScore> scores = new ArrayList<>();

    public Applicant() {}

    // Геттери та Сеттери
    public Long getId() { return id; }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setFaculty(Faculty faculty) { this.faculty = faculty; }
    public Faculty getFaculty() { return faculty; }

    public void setStatus(ApplicantStatus status) { this.status = status; }
    public ApplicantStatus getStatus() { return status; }

    public void addScore(ExamScore score) {
        scores.add(score);
        score.setApplicant(this);
    }
    public List<ExamScore> getScores() { return scores; }

    public double getTotalScore() {
        return scores.stream()
                .mapToDouble(ExamScore::getScore) // Беремо всі бали
                .sum();                           // І додаємо їх
    }
}