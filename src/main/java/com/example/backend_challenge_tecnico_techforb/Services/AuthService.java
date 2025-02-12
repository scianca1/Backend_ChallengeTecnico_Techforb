package com.example.backend_challenge_tecnico_techforb.Services;

import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import com.example.backend_challenge_tecnico_techforb.Repositorys.UserRepository;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.AuthResponse;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.LoginRequest;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.RegisterRequest;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
//@RequiredArgsConstructor
public class AuthService {


    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public AuthService(UserRepository userRepository,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
            String token = jwtService.getToken(user);
            Usuario u= userRepository.findByEmail(request.getEmail()).orElse(null);
            return AuthResponse.builder()
                    .token(token)
                    .nombreUsuario(u.getUsuario())
                    .rol(u.getRol())
                    .build();
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }


    }

    public AuthResponse register(RegisterRequest request) {
        Usuario u = Usuario.builder()
                .email(request.getEmail())
                .contracenia(passwordEncoder.encode(request.getPassword()))
                .usuario(request.getUsuario())
                .rol(Role.USER)
                .build();
        userRepository.save(u);

        return AuthResponse.builder()
                .token(jwtService.getToken(u))
                .build();
    }
}
