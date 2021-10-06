package com.example.reddit.repositories;

import com.example.reddit.entities.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    @Query("SELECT t FROM Topic as t WHERE t.name LIKE %:query%")
    List<Topic> searchQuery(@Param("query") String query);
}
