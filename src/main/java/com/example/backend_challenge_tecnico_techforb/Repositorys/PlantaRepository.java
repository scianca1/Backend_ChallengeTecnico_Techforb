package com.example.backend_challenge_tecnico_techforb.Repositorys;

import com.example.backend_challenge_tecnico_techforb.Dtos.Response.PlantaDtoResponse;
import com.example.backend_challenge_tecnico_techforb.Entitys.Planta;
import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantaRepository extends JpaRepository<Planta,Long> {
    @Query("SELECT  new com.example.backend_challenge_tecnico_techforb.Dtos.Response.PlantaDtoResponse(p.id,p.pais,p.nombre, " +
            "SUM(s.lecturasOK),SUM(s.alertasMedias),SUM(s.alertasRojas),p.usuario.id) "+
            "FROM Planta p "+
            "JOIN p.sensores s "+
            "GROUP BY p.id,p.nombre,p.pais")
    List<PlantaDtoResponse> obtenerPlantasDto();
}
