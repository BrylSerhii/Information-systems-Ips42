package com.example.admission.controller;

import com.example.admission.model.User;
import com.example.admission.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // РЕЄСТРАЦІЯ (Повертає створеного юзера)
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        // За замовчуванням даємо роль USER
        if (user.getRole() == null) user.setRole("USER");

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser); // <--- ВАЖЛИВО: Повертаємо об'єкт з ID!
    }

    // ВХІД (Новий метод для логіну)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // Тут має бути перевірка хешу пароля, але для навчальних цілей порівнюємо як є:
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity.ok(user); // <--- ВАЖЛИВО: Повертаємо юзера з ID
            }
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}