package com.example.admission.model;

import jakarta.persistence.*; // Якщо стара версія Java, то javax.persistence.*

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role; // Наприклад: "ROLE_USER", "ROLE_ADMIN"

    // --- КОНСТРУКТОРИ ---

    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- ГЕТТЕРИ ТА СЕТТЕРИ ---

    public Long getId() {
        return id;
    }

    // 👇 ОСЬ ЦЬОГО МЕТОДУ НЕ ВИСТАЧАЛО ДЛЯ ТЕСТІВ 👇
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}