package com.example.reddit.ressources;

import com.example.reddit.entities.Topic;
import com.example.reddit.repositories.TopicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TopicControllerTest {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    TopicController topicController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void before() {
        this.topicRepository.save(new Topic("submarine"));
        this.topicRepository.save(new Topic("butterfly"));
    }

    @Test
    public void get2Topics() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/topic?q=r"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void get1Topic() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/topic?q=sub"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
