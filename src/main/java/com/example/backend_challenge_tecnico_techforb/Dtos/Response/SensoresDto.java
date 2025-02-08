package com.example.backend_challenge_tecnico_techforb.Dtos.Response;

import com.example.backend_challenge_tecnico_techforb.Entitys.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensoresDto implements Serializable {

    private String nombre;

    private Long lecturasOK;

    private Long alertasMedias;

    private Long alertasRojas;

    public SensoresDto(Sensor sensor) {
        this.nombre= sensor.getNombre();
        this.lecturasOK=sensor.getLecturasOK();
        this.alertasMedias=sensor.getAlertasMedias();
        this.alertasRojas=sensor.getAlertasRojas();
    }
}
