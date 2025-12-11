package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class AdmissionService {

    private final ApplicantRepository applicantRepo;
    private final FacultyRepository facultyRepo;

    public AdmissionService(ApplicantRepository applicantRepo, FacultyRepository facultyRepo) {
        this.applicantRepo = applicantRepo;
        this.facultyRepo = facultyRepo;
    }

    /**
     * The Main Algorithm:
     * 1. Get all students for a faculty.
     * 2. Sort them by Total Score (Highest first).
     * 3. The top N students (where N is Quota) get "ADMITTED".
     * 4. The rest get "REJECTED".
     */
    @Transactional
    public void runAdmissionProcess(Long facultyId) {
        Faculty faculty = facultyRepo.findById(facultyId).orElseThrow();
        List<Applicant> applicants = applicantRepo.findByFacultyId(facultyId);

        // Sort: High score -> Low score
        applicants.sort(Comparator.comparingDouble(Applicant::getTotalScore).reversed());

        int quota = faculty.getBudgetQuota();

        for (int i = 0; i < applicants.size(); i++) {
            Applicant app = applicants.get(i);
            if (i < quota) {
                app.setStatus("ADMITTED");
            } else {
                app.setStatus("REJECTED");
            }
            applicantRepo.save(app);
        }
    }
}