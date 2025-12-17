package com.example.admission.service;

import com.example.admission.model.Applicant;
import com.example.admission.model.ApplicantStatus; // <--- Важливий імпорт
import com.example.admission.model.Faculty;
import com.example.admission.repository.ApplicantRepository;
import com.example.admission.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
public class AdmissionService {

    private final ApplicantRepository applicantRepository;
    private final FacultyRepository facultyRepository;

    public AdmissionService(ApplicantRepository applicantRepository, FacultyRepository facultyRepository) {
        this.applicantRepository = applicantRepository;
        this.facultyRepository = facultyRepository;
    }

    @Transactional
    public void processAdmissions() {
        List<Faculty> faculties = facultyRepository.findAll();

        for (Faculty faculty : faculties) {
            // 1. Беремо всіх, хто подав заявку на цей факультет
            List<Applicant> applicants = applicantRepository.findByFacultyId(faculty.getId());

            // 2. Сортуємо їх за балами (від найбільшого до найменшого)
            applicants.sort(Comparator.comparingDouble(Applicant::getTotalScore).reversed());

            // 3. Зараховуємо найкращих
            int capacity = faculty.getBudgetSeats() + faculty.getContractSeats();

            for (int i = 0; i < applicants.size(); i++) {
                Applicant app = applicants.get(i);

                if (i < capacity) {
                    // 👇 ВИПРАВЛЕНО: Використовуємо Enum, а не String
                    app.setStatus(ApplicantStatus.ADMITTED);
                } else {
                    app.setStatus(ApplicantStatus.REJECTED);
                }

                applicantRepository.save(app);
            }
        }
    }
}