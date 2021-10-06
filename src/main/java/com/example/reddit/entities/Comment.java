package com.example.reddit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String message;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;
}
