package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.ApplicantStatus; // Імпорт Enum
import com.example.admission.model.ExamScore;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdmissionServiceEdgeTest {

    @Mock private ApplicantRepository applicantRepo;
    @Mock private FacultyRepository facultyRepo;
    @InjectMocks private AdmissionService admissionService;

    @Test
    void shouldHandleEmptyApplicantList() {
        // SCENARIO: Факультет існує, але список заявок порожній.
        // ОЧІКУВАННЯ: Сервіс не має впасти і не має нікого зберігати.

        // 1. Підготовка даних (Mock)
        // Конструктор: Ім'я, Бюджет, Контракт
        Faculty mathFaculty = new Faculty("Math", 5, 5);
        // В тестах важливо вручну задавати ID, бо бази немає
        try {
            var field = Faculty.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(mathFaculty, 1L);
        } catch (Exception e) { e.printStackTrace(); }

        // Ми тепер використовуємо findAll(), а не findById()
        when(facultyRepo.findAll()).thenReturn(List.of(mathFaculty));
        when(applicantRepo.findByFacultyId(1L)).thenReturn(Collections.emptyList());

        // 2. Виконання
        admissionService.processAdmissions();

        // 3. Перевірка
        verify(applicantRepo, never()).save(any());
    }

    @Test
    void shouldHandleExactQuotaMatch() {
        // SCENARIO: Квота 1, подався 1 студент.
        // ОЧІКУВАННЯ: Студент має отримати статус ADMITTED.

        // 1. Підготовка факультету
        Faculty bioFaculty = new Faculty("Biology", 1, 0); // 1 місце
        try {
            var field = Faculty.class.getDeclaredField("id");
            field.setAccessible(true);
            field.set(bioFaculty, 2L);
        } catch (Exception e) { e.printStackTrace(); }

        // 2. Підготовка студента (старий конструктор видалено, юзаємо сеттери)
        Applicant student = new Applicant();
        student.setFaculty(bioFaculty);
        // Додамо бал, щоб уникнути NullPointerException при сортуванні
        student.addScore(new ExamScore("Biology", 180.0));

        // 3. Налаштування Mockito
        when(facultyRepo.findAll()).thenReturn(List.of(bioFaculty));
        when(applicantRepo.findByFacultyId(2L)).thenReturn(Collections.singletonList(student));

        // 4. Виконання
        admissionService.processAdmissions();

        // 5. Перевірка (використовуємо Enum ApplicantStatus)
        verify(applicantRepo, times(1)).save(argThat(app ->
                app.getStatus() == ApplicantStatus.ADMITTED
        ));
    }
}