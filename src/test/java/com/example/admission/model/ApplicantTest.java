package com.example.admission.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest {

    @Test
    void shouldCalculateTotalScoreCorrectly() {
        // GIVEN
        // Створюємо заявку через порожній конструктор
        Applicant applicant = new Applicant();

        // Додаємо бали (Math: 150, English: 160)
        applicant.addScore(new ExamScore("Mathematics", 150.0));
        applicant.addScore(new ExamScore("English", 160.0));

        // WHEN
        // Рахуємо суму
        double totalScore = applicant.getTotalScore();

        // THEN
        // Очікуємо 310.0
        assertEquals(310.0, totalScore, "Total score should be sum of all exams");
    }

    @Test
    void shouldLinkScoreToApplicantWhenAdding() {
        // GIVEN
        Applicant applicant = new Applicant();
        ExamScore score = new ExamScore("Physics", 180.0);

        // WHEN
        // Викликаємо наш метод addScore
        applicant.addScore(score);

        // THEN
        // 1. Перевіряємо, що бал є у списку
        assertTrue(applicant.getScores().contains(score));

        // 2. Перевіряємо зворотній зв'язок (що бал знає, чий він)
        assertEquals(applicant, score.getApplicant(), "Score should be linked to the applicant");
    }
}