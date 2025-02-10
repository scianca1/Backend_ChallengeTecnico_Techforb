package com.example.backend_challenge_tecnico_techforb.Controllers;

import com.example.backend_challenge_tecnico_techforb.Dtos.Request.PlantaDtoRequest;
import com.example.backend_challenge_tecnico_techforb.Services.PlantaService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Planta")
@RequiredArgsConstructor
public class PlantaController {
    @Autowired
    private PlantaService service;

    @PostMapping(value = "/nueva")
    public ResponseEntity<?> crear (@RequestBody PlantaDtoRequest planta,HttpServletRequest request){
        try {
            System.out.println("prueva= "+this.getTokenFromCookies(request));
            return ResponseEntity.ok(service.crear(planta));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error",e.getMessage()));
        }
    }
    private String getTokenFromCookies(HttpServletRequest request){
        if(request.getCookies()!=null){
            for(Cookie cookie:request.getCookies()){
                if("jwtToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    @GetMapping(value = "/prueva")
    public ResponseEntity<?> prueva (){
       return ResponseEntity.ok("prueva");
    }

    @GetMapping(value = "")
    public  ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.ok(service.getAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error",e.getMessage()));
        }
    }
    @GetMapping(value = "/Lecturas")
    public ResponseEntity<?>getAllLecturas(){
        try {
            return  ResponseEntity.ok(service.getAllLecturas());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error",e.getMessage()));
        }
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<?>getDetallePlanta(@PathVariable Long Id){
        try{
            return ResponseEntity.ok(service.getDetalle(Id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error",e.getMessage()));
        }
    }


}
