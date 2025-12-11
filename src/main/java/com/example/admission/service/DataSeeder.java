package com.example.admission.service;

import com.example.admission.repository.ApplicantRepository;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@EnableScheduling
public class DataSeeder {

    private final ApplicantRepository repository;
    private final Random random = new Random();
    private final String[] faculties = {"CS", "Economics", "Law", "Physics", "Arts"};

    public DataSeeder(ApplicantRepository repository) {
        this.repository = repository;
    }

    // Runs every 5 seconds to add data
    @Scheduled(fixedRate = 5000)
    public void generateStudent() {
        String faculty = faculties[random.nextInt(faculties.length)];
        double score = 120 + (80 * random.nextDouble());
        String name = "Student_" + random.nextInt(10000);

        Applicant s = new Applicant(name, faculty, score);
        repository.save(s);

        System.out.println(">>> Added: " + name);
    }
}