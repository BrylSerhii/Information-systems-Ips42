package com.example.admission.service;

import com.example.admission.model.Faculty;
import com.example.admission.repository.FacultyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final FacultyRepository facultyRepository;

    public DataSeeder(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Перевіряємо: якщо факультетів немає (0), то створюємо їх
        if (facultyRepository.count() == 0) {
            seedFaculties();
        }
    }

    private void seedFaculties() {
        // Створюємо 3 факультети з бюджетом і контрактом
        facultyRepository.save(new Faculty("Computer Science", 10, 20));
        facultyRepository.save(new Faculty("Cybersecurity", 5, 15));
        facultyRepository.save(new Faculty("Software Engineering", 12, 25));

        System.out.println("✅ Faculties seeded successfully!");
    }
}