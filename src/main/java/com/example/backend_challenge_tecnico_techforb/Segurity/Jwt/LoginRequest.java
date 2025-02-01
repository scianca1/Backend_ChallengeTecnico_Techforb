package com.example.backend_challenge_tecnico_techforb.Segurity.Jwt;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }
    public String getPassword(){
        return this.password;
    }
}