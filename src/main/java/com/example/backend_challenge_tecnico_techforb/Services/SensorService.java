package com.example.backend_challenge_tecnico_techforb.Services;

import com.example.backend_challenge_tecnico_techforb.Entitys.Planta;
import com.example.backend_challenge_tecnico_techforb.Entitys.Sensor;
import com.example.backend_challenge_tecnico_techforb.Repositorys.SensorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SensorService {
    @Autowired
    private SensorRepository repository;
    @Transactional
    public void generarSensores(Planta pCreada) {
        Sensor sensorTemperatura= Sensor.builder()
                                .nombre("Temperatura")
                                .habilitado(true)
                                .lecturasOK(getNroRandom())
                                .alertasMedias(getNroRandom())
                                .alertasRojas(getNroRandom())
                                .planta(pCreada)
                                .build();
        Sensor sensorPresion= Sensor.builder()
                              .nombre("Presion")
                              .habilitado(true)
                              .lecturasOK(getNroRandom())
                              .alertasMedias(getNroRandom())
                              .alertasRojas(getNroRandom())
                              .planta(pCreada)
                              .build();
        Sensor sensorViento= Sensor.builder()
                            .nombre("Viento")
                            .habilitado(true)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();
        Sensor sensorNiveles= Sensor.builder()
                            .nombre("Niveles")
                            .habilitado(true)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();
        Sensor sensorEnergia= Sensor.builder()
                            .nombre("Energia")
                            .habilitado(true)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();
        Sensor sensorTension= Sensor.builder()
                            .nombre("Tension")
                            .habilitado(false)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();
        Sensor sensorMonoxidoDeCarbono= Sensor.builder()
                            .nombre("Monoxido de carbono")
                            .habilitado(false)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();
        Sensor sensorOtrosGases= Sensor.builder()
                            .nombre("Otros gases")
                            .habilitado(false)
                            .lecturasOK(getNroRandom())
                            .alertasMedias(getNroRandom())
                            .alertasRojas(getNroRandom())
                            .planta(pCreada)
                            .build();

        repository.save(sensorTemperatura);
        repository.save(sensorPresion);
        repository.save(sensorViento);
        repository.save(sensorNiveles);
        repository.save(sensorEnergia);
        repository.save(sensorTension);
        repository.save(sensorMonoxidoDeCarbono);
        repository.save(sensorOtrosGases);
    }
    private Long getNroRandom(){
            return Long.valueOf((long)( Math.random()*1001));
    }
}
