package com.example.admission.repository;
import com.example.admission.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FacultyRepository extends JpaRepository<Faculty, Long> {}