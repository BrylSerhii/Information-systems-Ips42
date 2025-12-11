package com.example.admission.repository;
import com.example.admission.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    List<Applicant> findByFacultyId(Long facultyId);
}