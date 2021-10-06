package com.example.reddit.ressources;

import com.example.reddit.DTO.LoginDTO;
import com.example.reddit.DTO.LoginResponseDTO;
import com.example.reddit.services.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    private final LoginService loginService;

    public AuthController(
            LoginService loginService
    ) {
        this.loginService = loginService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginDTO loginDto) {
        LoginResponseDTO loginResponseDto = this.loginService.login(loginDto);
        return ResponseEntity.ok(loginResponseDto);
    }
}
