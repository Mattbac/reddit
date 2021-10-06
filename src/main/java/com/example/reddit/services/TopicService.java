package com.example.reddit.services;

import com.example.reddit.entities.Topic;
import com.example.reddit.repositories.TopicRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class TopicService {

    private final TopicRepository topicRepository;

    private final ModelMapper modelMapper;

    public TopicService(
            TopicRepository topicRepository,
            ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
        this.topicRepository = topicRepository;
    }

    public List<Topic> search(String query) {
        return this.topicRepository.searchQuery(query);
    }
}
