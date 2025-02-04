package com.example.backend_challenge_tecnico_techforb.Controllers;

import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.AuthResponse;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.LoginRequest;
import com.example.backend_challenge_tecnico_techforb.Segurity.Jwt.RegisterRequest;
import com.example.backend_challenge_tecnico_techforb.Services.AuthService;
import com.example.backend_challenge_tecnico_techforb.Utils.EmailValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private  AuthService authservice;
    @PostMapping(value="/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse response){
        try {
            AuthResponse authrespose=authservice.login(request);
            String token = authrespose.getToken();
            String userName= authrespose.getNombreUsuario();
            boolean isSecure = httpServletRequest.getScheme().equals("https"); // Verifica si la solicitud es HTTPS

            Cookie jwtCookie = new Cookie("jwtToken", token);
            jwtCookie.setHttpOnly(false); // Evita el acceso desde JavaScript
            jwtCookie.setSecure(isSecure); // Solo se envía por HTTPS
            jwtCookie.setPath("/"); // Disponible en toda la app
            jwtCookie.setMaxAge(24 * 60 * 60); // Tiempo de vida en segundos (1 día)


            Cookie UserCookie = new Cookie("UserName", userName);
            UserCookie.setHttpOnly(false); // Evita el acceso desde JavaScript
            UserCookie.setSecure(isSecure); // Solo se envía por HTTPS
            UserCookie.setPath("/"); // Disponible en toda la app
            UserCookie.setMaxAge(24 * 60 * 60); // Tiempo de vida en segundos (1 día)

            response.addCookie(jwtCookie);
            response.addCookie(UserCookie);
            return ResponseEntity.ok(Map.of("respose","Autenticacion exitosa!"));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }
    @PostMapping(value="/register")
    public ResponseEntity<?> register (@RequestBody RegisterRequest request){
        try {
            if(request.getPassword().length()>=8
                    && EmailValidator.esEmailValido(request.getEmail())
                    &&!request.getUsuario().isEmpty()
                    &&request.getRepetidaPassword().equals(request.getPassword())){
                authservice.register(request);
                return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("respose","Usuario Registrado exitosamente!"));
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("response","Alguno de los campos no cumple con los requerimiento: Usuario no puede estar vacio," +
                        " Contraceña deve tener al menos 8 caracteres," +
                        " Contraceña debe ser igual en los dos ultimos campos," +
                        " El email debe ser un mail valido"));
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping ("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    // Establece la cookie con valor vacío, maxAge 0 para eliminarla
                    cookie.setValue(null);
                    cookie.setMaxAge(0); // Expira inmediatamente
                    cookie.setHttpOnly(true);
                    cookie.setSecure(true); // Cambia a true si usas HTTPS
                    cookie.setPath("/"); // Asegúrate de que la ruta coincida con la original
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return ResponseEntity.ok("Logged out exitosa!");
    }
}

