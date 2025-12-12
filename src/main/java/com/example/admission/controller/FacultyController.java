package com.example.admission.controller;

import com.example.admission.model.Faculty;
import com.example.admission.repository.FacultyRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FacultyController {

    private final FacultyRepository repository;

    public FacultyController(FacultyRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/faculties")
    public List<Faculty> getAllFaculties() {
        return repository.findAll();
    }
}