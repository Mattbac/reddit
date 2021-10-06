package com.example.reddit.ressources;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class loginControllerTest {

    @Autowired
    AuthController authController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void loginSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .content("{\"email\": \"email\", \"password\": \"password\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void loginMissingPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .content("{\"email\": \"email\", \"password\": \"\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void loginMissingEmail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .content("{\"email\": \"\", \"password\": \"password\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
