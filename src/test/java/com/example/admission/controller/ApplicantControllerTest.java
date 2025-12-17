package com.example.admission.controller;

import com.example.admission.config.JwtRequestFilter; // Додаємо мок для фільтра безпеки
import com.example.admission.dto.ApplicantRequest;
import com.example.admission.model.Applicant;
import com.example.admission.model.Faculty;
import com.example.admission.model.User;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import com.example.admission.repository.UserRepository;
import com.example.admission.security.JwtUtil; // Додаємо мок для утиліти безпеки
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApplicantController.class)
@AutoConfigureMockMvc(addFilters = false) // Вимикаємо Spring Security фільтри для тестів
class ApplicantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicantRepository applicantRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private UserRepository userRepository;

    // Мокаємо компоненти безпеки, щоб контекст піднявся без помилок
    @MockBean
    private JwtRequestFilter jwtRequestFilter;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldSubmitApplicationSuccessfully() throws Exception {
        // GIVEN: Підготовка даних

        // 1. Створюємо тестового юзера
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setUsername("testStudent");

        // 2. Створюємо тестовий факультет
        Long facultyId = 10L;
        Faculty mockFaculty = new Faculty("Computer Science", 10, 20);
        // Оскільки в тестах немає бази, ID треба встановити вручну (або ігнорувати)

        // 3. Формуємо запит (DTO), який прийде з фронтенду
        ApplicantRequest request = new ApplicantRequest();
        request.setUserId(userId);
        request.setFacultyId(facultyId);
        request.setMathScore(190.0);
        request.setEnglishScore(180.0);
        request.setPhysicsScore(175.0);

        // 4. Налаштовуємо поведінку моків (Fake Database)
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(mockFaculty));

        // Коли репозиторій просять зберегти, він просто повертає те, що йому дали
        when(applicantRepository.save(any(Applicant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // WHEN & THEN: Виконуємо запит і перевіряємо результат
        mockMvc.perform(post("/applicants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Перетворюємо об'єкт в JSON
                .andExpect(status().isOk()) // Очікуємо статус 200 OK
                .andExpect(content().string("Application submitted successfully!")); // Очікуємо повідомлення
    }
}