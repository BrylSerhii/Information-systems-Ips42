package com.example.admission.controller;

import com.example.admission.model.Applicant;
import com.example.admission.repository.ApplicantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ApplicantController.class)
class ApplicantControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simulates the HTTP request

    @MockBean
    private ApplicantRepository repository; // Fake the database connection

    @Test
    void shouldReturnListOfApplicants() throws Exception {
        // 1. Arrange: Create fake data
        Applicant app1 = new Applicant("Student A", null);
        Applicant app2 = new Applicant("Student B", null);
        List<Applicant> fakeList = Arrays.asList(app1, app2);

        // Tell the fake DB to return our list
        when(repository.findAll()).thenReturn(fakeList);

        // 2. Act & 3. Assert
        mockMvc.perform(get("/applicants"))
                .andExpect(status().isOk()) // Expect HTTP 200
                .andExpect(jsonPath("$.size()").value(2)) // Expect 2 items in JSON
                .andExpect(jsonPath("$[0].fullName").value("Student A")); // Verify data
    }

    @Test
    void shouldAllowCors() throws Exception {
        // Verify that the backend allows the React frontend (localhost:3000) to talk to it
        mockMvc.perform(get("/applicants")
                        .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk());
    }
}