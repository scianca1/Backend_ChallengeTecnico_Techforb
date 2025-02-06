package com.example.backend_challenge_tecnico_techforb.Repositorys;

import com.example.backend_challenge_tecnico_techforb.Entitys.Planta;
import com.example.backend_challenge_tecnico_techforb.Entitys.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {
}
