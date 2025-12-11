package com.example.admission.model;

import jakarta.persistence.*;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer budgetQuota;

    public Faculty() {}

    public Faculty(String name, Integer budgetQuota) {
        this.name = name;
        this.budgetQuota = budgetQuota;
    }

    // --- ADDED MISSING GETTER ---
    public Long getId() { return id; }

    public String getName() { return name; }
    public Integer getBudgetQuota() { return budgetQuota; }
}