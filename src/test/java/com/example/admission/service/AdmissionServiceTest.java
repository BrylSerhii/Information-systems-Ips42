package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.ExamScore;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceTest {

    @Mock
    private ApplicantRepository applicantRepo;

    @Mock
    private FacultyRepository facultyRepo;

    @InjectMocks
    private AdmissionService admissionService;

    @Test
    void shouldAdmitTopStudentsAndRejectOthers() {
        // 1. SETUP: Create a Fake Faculty with Quota = 2
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Computer Science", 2);

        // 2. SETUP: Create 3 Fake Applicants with scores
        Applicant studentHigh = createApplicant("Alice", 180.0);
        Applicant studentMid = createApplicant("Bob", 160.0);
        Applicant studentLow = createApplicant("Charlie", 140.0);

        // The repository normally returns them in random order, let's simulate that
        List<Applicant> students = new ArrayList<>(Arrays.asList(studentLow, studentHigh, studentMid));

        // 3. MOCK: Tell the repositories what to return when called
        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(faculty));
        when(applicantRepo.findByFacultyId(facultyId)).thenReturn(students);

        // 4. ACTION: Run the actual business logic
        admissionService.runAdmissionProcess(facultyId);

        // 5. VERIFY: Did the logic work?
        // Alice (180) -> Should be ADMITTED (Rank 1)
        assertEquals("ADMITTED", studentHigh.getStatus());

        // Bob (160) -> Should be ADMITTED (Rank 2)
        assertEquals("ADMITTED", studentMid.getStatus());

        // Charlie (140) -> Should be REJECTED (Rank 3, but Quota is 2)
        assertEquals("REJECTED", studentLow.getStatus());

        // Verify that we actually saved the changes to the database
        verify(applicantRepo, times(3)).save(any(Applicant.class));
    }

    @Test
    void shouldAdmitEveryoneIfQuotaIsNotReached() {
        // 1. SETUP: Quota = 5
        Long facultyId = 1L;
        Faculty faculty = new Faculty("Physics", 5);

        // 2. SETUP: Only 2 students apply
        Applicant s1 = createApplicant("Alice", 150.0);
        Applicant s2 = createApplicant("Bob", 140.0);
        List<Applicant> students = Arrays.asList(s1, s2);

        // 3. MOCK
        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(faculty));
        when(applicantRepo.findByFacultyId(facultyId)).thenReturn(students);

        // 4. ACTION
        admissionService.runAdmissionProcess(facultyId);

        // 5. VERIFY
        assertEquals("ADMITTED", s1.getStatus());
        assertEquals("ADMITTED", s2.getStatus());
    }

    // Helper method to create students quickly
    private Applicant createApplicant(String name, Double score) {
        Applicant app = new Applicant(name, null);
        // We cheat a bit and set the total score directly by adding a dummy exam
        app.addScore(new ExamScore("Test", score));
        return app;
    }
}