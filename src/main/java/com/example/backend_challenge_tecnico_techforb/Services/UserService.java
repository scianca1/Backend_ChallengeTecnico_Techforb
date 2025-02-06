package com.example.backend_challenge_tecnico_techforb.Services;

import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import com.example.backend_challenge_tecnico_techforb.Repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

public Usuario getUsuarioByEmail(String email){
    return repository.findByEmail(email).orElseThrow(()-> new RuntimeException("Tenemos problamas para encontrar tu email"));
}
}
