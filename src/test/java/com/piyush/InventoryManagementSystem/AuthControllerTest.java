package com.piyush.InventoryManagementSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterUser_success() throws Exception {

        // 🔹 Request JSON
        String requestJson = """
        {
          "name": "Piyush",
          "email": "piyush@test.com",
          "password": "123456",
          "phoneNumber": "9999999999",
          "role": "ADMIN"
        }
        """;

        mockMvc.perform(post("/api/auth/register")   // 👉 change endpoint if needed
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("user created successfully"));
    }

    //Wrong email id, missing @
    @Test
    void testRegisterUser_invalidEmail() throws Exception {
        String requestJson = """
            {
              "name": "Test",
              "email": "piyush123gmail.com",
              "password": "123456",
              "phoneNumber": "9999999999",
              "role": "ADMIN"
            }
            """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRegisterUser_invalidRole() throws Exception {

        String requestJson = """
    {
      "name": "Test",
      "email": "test@mail.com",
      "password": "123456",
      "phoneNumber": "9999999999",
      "role": "ROLE"
    }
    """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }


    @Test
    void testRegisterUser_emptyPassword() throws Exception {

        String requestJson = """
    {
      "name": "Test",
      "email": "test@mail.com",
      "password": "",
      "phoneNumber": "9999999999",
      "role": "ADMIN"
    }
    """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isBadRequest());
    }
}
