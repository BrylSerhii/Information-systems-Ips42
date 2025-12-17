package com.example.admission.controller;

// 👇 ОСЬ ЦІ ІМПОРТИ ВИПРАВЛЯТЬ ЧЕРВОНІ ПОМИЛКИ 👇
import com.example.admission.dto.ApplicantRequest;
import com.example.admission.model.*;
import com.example.admission.repository.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applicants")
public class ApplicantController {

    private final ApplicantRepository applicantRepository;
    private final FacultyRepository facultyRepository;
    private final UserRepository userRepository;

    // Конструктор
    public ApplicantController(ApplicantRepository applicantRepository,
                               FacultyRepository facultyRepository,
                               UserRepository userRepository) {
        this.applicantRepository = applicantRepository;
        this.facultyRepository = facultyRepository;
        this.userRepository = userRepository;
    }

    // Отримати список всіх заявок
    @GetMapping
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    // Подати нову заявку (З РУЧНИМ ВВЕДЕННЯМ БАЛІВ)
    @PostMapping
    public ResponseEntity<?> applyForRegistration(@RequestBody ApplicantRequest request) {

        // 1. Шукаємо користувача
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        // 2. Шукаємо факультет
        Faculty faculty = facultyRepository.findById(request.getFacultyId())
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + request.getFacultyId()));

        // 3. Створюємо заявку
        Applicant applicant = new Applicant();
        applicant.setUser(user);
        applicant.setFaculty(faculty);

        // Встановлюємо статус (переконайтеся, що ApplicantStatus імпортовано)
        applicant.setStatus(ApplicantStatus.APPLIED);

        // 4. ВСТАНОВЛЮЄМО БАЛИ
        // Беремо числа, які прийшли з фронтенду
        applicant.addScore(new ExamScore("Mathematics", request.getMathScore()));
        applicant.addScore(new ExamScore("English", request.getEnglishScore()));
        applicant.addScore(new ExamScore("Physics", request.getPhysicsScore()));

        // 5. Зберігаємо в базу
        applicantRepository.save(applicant);

        return ResponseEntity.ok("Application submitted successfully!");
    }
}