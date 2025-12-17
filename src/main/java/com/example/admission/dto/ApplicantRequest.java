package com.example.admission.dto;

public class ApplicantRequest {
    private Long userId;
    private Long facultyId;

    // Поля для балів
    private Double mathScore;
    private Double englishScore;
    private Double physicsScore;

    // Геттери
    public Long getUserId() { return userId; }
    public Long getFacultyId() { return facultyId; }
    public Double getMathScore() { return mathScore; }
    public Double getEnglishScore() { return englishScore; }
    public Double getPhysicsScore() { return physicsScore; }

    // Сеттери
    public void setUserId(Long userId) { this.userId = userId; }
    public void setFacultyId(Long facultyId) { this.facultyId = facultyId; }
    public void setMathScore(Double mathScore) { this.mathScore = mathScore; }
    public void setEnglishScore(Double englishScore) { this.englishScore = englishScore; }
    public void setPhysicsScore(Double physicsScore) { this.physicsScore = physicsScore; }
}