package com.example.reddit.services;

import com.example.reddit.DTO.UserCreateDTO;
import com.example.reddit.DTO.UserDTO;
import com.example.reddit.DTO.UserUpdateDTO;
import com.example.reddit.entities.User;
import com.example.reddit.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public UserService(
        UserRepository userRepository,
        ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    public UserDTO get(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) return null;
        return this.modelMapper.map(user.get(), UserDTO.class);
    }

    public UserDTO create(UserCreateDTO userCreateDTO) {
        User user = this.modelMapper.map(userCreateDTO, User.class);
        user = this.userRepository.save(user);
        return this.modelMapper.map(user, UserDTO.class);
    }

    public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
        Optional<User> opUser = this.userRepository.findById(id);
        if (opUser.isEmpty()) return null;
        User user = opUser.get();
        if (userUpdateDTO.getEmail() != null) user.setEmail(userUpdateDTO.getEmail());
        if (userUpdateDTO.getPassword() != null) user.setPassword(userUpdateDTO.getPassword());
        if (userUpdateDTO.getFirstName() != null) user.setFirstName(userUpdateDTO.getFirstName());
        if (userUpdateDTO.getLastName() != null) user.setLastName(userUpdateDTO.getLastName());
        user = this.userRepository.save(user);
        return this.modelMapper.map(user, UserDTO.class);
    }

    public UserDTO delete(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) return null;
        this.userRepository.deleteById(id);
        return this.modelMapper.map(user.get(), UserDTO.class);
    }
}
