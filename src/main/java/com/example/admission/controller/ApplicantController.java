package com.example.admission.controller;

import com.example.admission.model.Applicant; // <--- THIS WAS LIKELY MISSING
import com.example.admission.repository.ApplicantRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicantController {

    private final ApplicantRepository repository;

    public ApplicantController(ApplicantRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants() {
        return repository.findAll();
    }
}