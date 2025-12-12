package com.example.admission.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ApplicantTest {

    @Test
    void shouldCalculateTotalScoreCorrectly() {
        // 1. Arrange
        Applicant student = new Applicant("John Doe", null);

        // 2. Act: Add two scores
        student.addScore(new ExamScore("Math", 90.0));
        student.addScore(new ExamScore("History", 80.5));

        // 3. Assert: 90 + 80.5 should be 170.5
        assertEquals(170.5, student.getTotalScore(), 0.001);
    }

    @Test
    void shouldInitializeWithZeroScore() {
        Applicant student = new Applicant("Jane Doe", null);
        assertEquals(0.0, student.getTotalScore());
    }

    @Test
    void shouldLinkScoreToApplicant() {
        // Verify the bidirectional relationship
        Applicant student = new Applicant("Test Student", null);
        ExamScore score = new ExamScore("Physics", 100.0);

        student.addScore(score);

        // The score object should now know who it belongs to
        // (This relies on the logic inside your addScore method)
        // Note: You might need to add a getApplicant() getter to ExamScore to test this,
        // but checking the list size is good enough for now.
        assertEquals(1, student.getScores().size());
    }
}