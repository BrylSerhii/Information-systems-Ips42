package com.example.admission.controller;

import com.example.admission.model.Applicant;
import com.example.admission.repository.ApplicantRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApplicantController {

    private final ApplicantRepository repository;

    public ApplicantController(ApplicantRepository repository) {
        this.repository = repository;
    }

    // Visiting http://localhost:8080/applicants will trigger this
    @GetMapping("/applicants")
    public List<Applicant> getAllApplicants() {
        return repository.findAll();
    }
}