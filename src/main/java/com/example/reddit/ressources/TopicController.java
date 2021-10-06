package com.example.reddit.ressources;

import com.example.reddit.entities.Topic;
import com.example.reddit.services.TopicService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topic")
public class TopicController {

    private final TopicService topicService;

    public TopicController(
        TopicService topicService
    ) {
        this.topicService = topicService;
    }

    @GetMapping("")
    public List<Topic> getTopics(@RequestParam String q) {
        List<Topic> topics = this.topicService.search(q);
        return topics;
    }
}
