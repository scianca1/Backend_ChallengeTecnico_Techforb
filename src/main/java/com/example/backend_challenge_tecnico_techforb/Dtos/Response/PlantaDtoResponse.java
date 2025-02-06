package com.example.backend_challenge_tecnico_techforb.Dtos.Response;

import com.example.backend_challenge_tecnico_techforb.Entitys.Planta;
import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlantaDtoResponse implements Serializable {

    private Long id;

    private String pais;

    private String nombre;

    private Long lecturasOk;

    private Long alertasMadias;

    private Long alertasRojas;


    private Long idUsuario;

    public PlantaDtoResponse(Planta planta) {
        this.nombre= planta.getNombre();
        this.pais=planta.getPais();
        this.id=planta.getId();
        this.idUsuario=planta.getUsuario().getId();
    }
}
