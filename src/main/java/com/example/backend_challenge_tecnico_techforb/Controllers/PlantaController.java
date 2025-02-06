package com.example.backend_challenge_tecnico_techforb.Controllers;

import com.example.backend_challenge_tecnico_techforb.Dtos.Request.PlantaDtoRequest;
import com.example.backend_challenge_tecnico_techforb.Services.PlantaService;
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
    public ResponseEntity<?> crear (@RequestBody PlantaDtoRequest planta){
        try {
            return ResponseEntity.ok(service.crear(planta));
        }catch (RuntimeException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("Error",e.getMessage()));
        }
    }
    @GetMapping(value = "/prueva")
    public ResponseEntity<?> prueva (){
       return ResponseEntity.ok("prueva");
    }

    @GetMapping(value = "")
    public  ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }
}
