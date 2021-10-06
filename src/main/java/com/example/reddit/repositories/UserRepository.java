package com.example.reddit.repositories;

import com.example.reddit.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
