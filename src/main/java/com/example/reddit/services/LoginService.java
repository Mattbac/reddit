package com.example.reddit.services;

import com.example.reddit.DTO.LoginDTO;
import com.example.reddit.DTO.LoginResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class LoginService {

    private final ModelMapper modelMapper;

    public LoginService(
            ModelMapper modelMapper
    ) {
        this.modelMapper = modelMapper;
    }

    public LoginResponseDTO login(LoginDTO loginDto) {
        return new LoginResponseDTO("bearer");
    }
}
