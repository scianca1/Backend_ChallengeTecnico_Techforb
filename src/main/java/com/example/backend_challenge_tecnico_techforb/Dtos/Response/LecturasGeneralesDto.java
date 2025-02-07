package com.example.backend_challenge_tecnico_techforb.Dtos.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturasGeneralesDto implements Serializable {
    private String text;
    private Long nro;
}
