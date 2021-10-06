package com.example.reddit.DTO;

public class LoginResponseDTO {

    private String bearer;

    public LoginResponseDTO(String bearer) {
        this.bearer = bearer;
    }

    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "bearer='" + bearer + '\'' +
                '}';
    }
}
