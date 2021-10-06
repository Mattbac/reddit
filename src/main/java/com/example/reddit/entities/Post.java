package com.example.reddit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private String media;

    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;
}
