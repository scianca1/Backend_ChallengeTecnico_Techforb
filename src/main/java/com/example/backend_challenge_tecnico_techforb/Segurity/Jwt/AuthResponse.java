package com.example.backend_challenge_tecnico_techforb.Segurity.Jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String nombreUsuario;
    private String token;
}
