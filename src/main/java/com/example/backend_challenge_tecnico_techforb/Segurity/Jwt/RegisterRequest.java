package com.example.backend_challenge_tecnico_techforb.Segurity.Jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String usuario;
    private String email;
    private String password;
    private String repetidaPassword;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsuario() {
        return usuario;
    }
}
