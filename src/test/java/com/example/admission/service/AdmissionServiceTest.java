package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.ApplicantStatus;
import com.example.admission.model.ExamScore;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceTest {

    @Mock
    private ApplicantRepository applicantRepository;

    @Mock
    private FacultyRepository facultyRepository;

    @InjectMocks
    private AdmissionService admissionService;

    @Test
    void shouldAdmitQualifiedApplicants() {
        // GIVEN
        // 1. Створюємо факультет (Тепер 3 аргументи: Ім'я, Бюджет, Контракт)
        Faculty csFaculty = new Faculty("Computer Science", 1, 1); // Всього 2 місця

        // Встановлюємо ID вручну для тесту
        try {
            var field = Faculty.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(csFaculty, 1L);
        } catch (Exception e) { e.printStackTrace(); }

        // 2. Створюємо двох студентів
        // Студент 1 (Сильний)
        Applicant student1 = new Applicant();
        student1.setFaculty(csFaculty);
        student1.addScore(new ExamScore("Math", 200.0)); // Total: 200

        // Студент 2 (Слабкий)
        Applicant student2 = new Applicant();
        student2.setFaculty(csFaculty);
        student2.addScore(new ExamScore("Math", 100.0)); // Total: 100

        // Налаштовуємо Mock-базу
        when(facultyRepository.findAll()).thenReturn(Collections.singletonList(csFaculty));
        when(applicantRepository.findByFacultyId(1L)).thenReturn(Arrays.asList(student1, student2));

        // WHEN
        admissionService.processAdmissions();

        // THEN
        // Перевіряємо, що збереглися обидва (обидва влізли, бо місць 2)
        verify(applicantRepository, times(2)).save(argThat(applicant ->
                applicant.getStatus() == ApplicantStatus.ADMITTED
        ));
    }

    @Test
    void shouldRejectApplicantWhenNoSeats() {
        // GIVEN
        // Факультет з 0 місць (Бюджет 0, Контракт 0)
        Faculty fullFaculty = new Faculty("Popular One", 0, 0);
        try {
            var field = Faculty.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(fullFaculty, 2L);
        } catch (Exception e) { e.printStackTrace(); }

        Applicant student = new Applicant();
        student.setFaculty(fullFaculty);
        student.addScore(new ExamScore("Math", 200.0));

        when(facultyRepository.findAll()).thenReturn(Collections.singletonList(fullFaculty));
        when(applicantRepository.findByFacultyId(2L)).thenReturn(Collections.singletonList(student));

        // WHEN
        admissionService.processAdmissions();

        // THEN
        // Студент має отримати статус REJECTED
        verify(applicantRepository, times(1)).save(argThat(applicant ->
                applicant.getStatus() == ApplicantStatus.REJECTED
        ));
    }
}