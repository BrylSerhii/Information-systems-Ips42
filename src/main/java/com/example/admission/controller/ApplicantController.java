package com.example.admission.controller;

import com.example.admission.model.Applicant;
import com.example.admission.model.ExamScore;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApplicantController {

    private final ApplicantRepository applicantRepository;
    private final FacultyRepository facultyRepository;

    // Constructor injection for both repositories
    public ApplicantController(ApplicantRepository applicantRepository, FacultyRepository facultyRepository) {
        this.applicantRepository = applicantRepository;
        this.facultyRepository = facultyRepository;
    }

    // 1. GET: List all students (Used by Results Page)
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    // 2. POST: Register a new student (Used by Apply Page)
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/applicants/apply")
    public Applicant applyForRegistration(@RequestBody Map<String, String> payload) {
        // 1. Extract data from the JSON sent by React
        String name = payload.get("fullName");
        Long facultyId = Long.parseLong(payload.get("facultyId"));

        // 2. Find the Faculty they want to join
        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + facultyId));

        // 3. Create the new Applicant
        Applicant newApp = new Applicant(name, faculty);

        // 4. Simulate Entrance Exams (Give them random scores between 100 and 200)
        // In a real system, you would enter these manually later.
        newApp.addScore(new ExamScore("Math", Math.random() * 100 + 100));
        newApp.addScore(new ExamScore("History", Math.random() * 100 + 100));

        // 5. Save to Database
        return applicantRepository.save(newApp);
    }
}