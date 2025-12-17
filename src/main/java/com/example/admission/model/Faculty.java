package com.example.admission.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int budgetSeats;   // Бюджетні місця
    private int contractSeats; // Контрактні місця

    // Зв'язок з заявками (один факультет -> багато заявок)
    @OneToMany(mappedBy = "faculty")
    private List<Applicant> applicants;


    // --- КОНСТРУКТОРИ ---

    // Порожній конструктор (обов'язковий для Hibernate)
    public Faculty() {
    }


    // Конструктор для DataSeeder (щоб створювати факультети одразу з місцями)
    public Faculty(String name, int budgetSeats, int contractSeats) {
        this.name = name;
        this.budgetSeats = budgetSeats;
        this.contractSeats = contractSeats;
    }

    // --- ГЕТТЕРИ ТА СЕТТЕРИ (Це те, що шукає AdmissionService) ---

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudgetSeats() {
        return budgetSeats;
    }

    public void setBudgetSeats(int budgetSeats) {
        this.budgetSeats = budgetSeats;
    }

    public int getContractSeats() {
        return contractSeats;
    }

    public void setContractSeats(int contractSeats) {
        this.contractSeats = contractSeats;
    }
    @JsonIgnore
    public List<Applicant> getApplicants() {
        return applicants;
    }
}