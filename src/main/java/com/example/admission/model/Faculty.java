package com.example.admission.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer budgetQuota; // The "Plan" (e.g., 10 places)

    public Faculty() {}

    public Faculty(String name, Integer budgetQuota) {
        this.name = name;
        this.budgetQuota = budgetQuota;
    }

    // Getters
    public String getName() { return name; }
    public Integer getBudgetQuota() { return budgetQuota; }
}