package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.ExamScore;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataSeeder implements CommandLineRunner {

    private final FacultyRepository facultyRepo;
    private final ApplicantRepository applicantRepo;
    private final AdmissionService admissionService;

    public DataSeeder(FacultyRepository facultyRepo, ApplicantRepository applicantRepo, AdmissionService admissionService) {
        this.facultyRepo = facultyRepo;
        this.applicantRepo = applicantRepo;
        this.admissionService = admissionService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (facultyRepo.count() > 0) return;

        Faculty cs = facultyRepo.save(new Faculty("Computer Science", 5));
        Faculty law = facultyRepo.save(new Faculty("Law", 3));

        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            Faculty targetFaculty = (i % 2 == 0) ? cs : law;
            Applicant app = new Applicant("Student " + i, targetFaculty);

            app.addScore(new ExamScore("Math", 100 + rand.nextDouble() * 100));
            app.addScore(new ExamScore("History", 100 + rand.nextDouble() * 100));

            applicantRepo.save(app);
        }

        System.out.println(">>> Calculating Admission Results...");
        admissionService.runAdmissionProcess(cs.getId());  // Now this will work!
        admissionService.runAdmissionProcess(law.getId()); // This too!
        System.out.println(">>> Done! Check the database.");
    }
}