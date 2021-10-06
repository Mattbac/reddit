package com.example.reddit.ressources;

import com.example.reddit.entities.User;
import com.example.reddit.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
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

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthController authController;

    @Autowired
    private MockMvc mockMvc;

    private User user = new User("martin", "thomas", "thomas.martin@gmail.com", "6qsdf3df5s");

    @Before
    public void before() {
        this.user = this.userRepository.save(this.user);
    }

    @Test
    public void getUser1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", this.user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getUserFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/{id}", 5986))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void createUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .content("{" +
                                "\"firstName\": \"martin\"," +
                                "\"lastName\": \"thomas\"," +
                                "\"email\": \"thomas.martin@gmail.com\"," +
                                "\"password\": \"d6f9sdfg5fg\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void createUserMissingFirstName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user")
                        .content("{" +
                                "\"lastName\": \"thomas\"," +
                                "\"email\": \"thomas.martin@gmail.com\"," +
                                "\"password\": \"d6f9sdfg5fg\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{id}", this.user.getId())
                        .content("{" +
                                "\"firstName\": \"Yohan\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());

        User user = this.userRepository.findById(this.user.getId()).get();
        Assert.assertEquals(user.getFirstName(), "Yohan");
    }

    @Test
    public void updateUserFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/user/{id}", 5265)
                        .content("{" +
                                "\"firstName\": \"Yohan\"" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{id}", this.user.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteUserFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/{id}", 58565))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
