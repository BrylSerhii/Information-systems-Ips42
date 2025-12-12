package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceEdgeTest {

    @Mock private ApplicantRepository applicantRepo;
    @Mock private FacultyRepository facultyRepo;
    @InjectMocks private AdmissionService admissionService;

    @Test
    void shouldHandleEmptyApplicantList() {
        // Scenario: Faculty exists, but NO ONE applied.
        // The code should not crash (NullPointerException).

        Long facultyId = 1L;
        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(new Faculty("Math", 5)));
        when(applicantRepo.findByFacultyId(facultyId)).thenReturn(Collections.emptyList());

        // Run logic
        admissionService.runAdmissionProcess(facultyId);

        // Verify we never tried to save anything (since list was empty)
        verify(applicantRepo, never()).save(any());
    }

    @Test
    void shouldHandleExactQuotaMatch() {
        // Scenario: Quota is 1, and exactly 1 student applied.
        // They should be ADMITTED.

        Long facultyId = 1L;
        Faculty faculty = new Faculty("Biology", 1);
        Applicant student = new Applicant("Lone Wolf", faculty);

        when(facultyRepo.findById(facultyId)).thenReturn(Optional.of(faculty));
        when(applicantRepo.findByFacultyId(facultyId)).thenReturn(Collections.singletonList(student));

        admissionService.runAdmissionProcess(facultyId);

        verify(applicantRepo, times(1)).save(argThat(app ->
                app.getStatus().equals("ADMITTED")
        ));
    }
}