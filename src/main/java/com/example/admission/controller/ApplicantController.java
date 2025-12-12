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

    public ApplicantController(ApplicantRepository applicantRepository, FacultyRepository facultyRepository) {
        this.applicantRepository = applicantRepository;
        this.facultyRepository = facultyRepository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants() {
        return applicantRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/applicants/apply")
    public Applicant applyForRegistration(@RequestBody Map<String, String> payload) {
        String name = payload.get("fullName");
        Long facultyId = Long.parseLong(payload.get("facultyId"));

        Faculty faculty = facultyRepository.findById(facultyId)
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + facultyId));

        Applicant newApp = new Applicant(name, faculty);

        newApp.addScore(new ExamScore("Math", Math.random() * 100 + 100));
        newApp.addScore(new ExamScore("History", Math.random() * 100 + 100));

        return applicantRepository.save(newApp);
    }
}