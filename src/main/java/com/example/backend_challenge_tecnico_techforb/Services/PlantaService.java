package com.example.backend_challenge_tecnico_techforb.Services;

import com.example.backend_challenge_tecnico_techforb.Dtos.Request.PlantaDtoRequest;
import com.example.backend_challenge_tecnico_techforb.Dtos.Response.LecturasGeneralesDto;
import com.example.backend_challenge_tecnico_techforb.Dtos.Response.PlantaDtoResponse;
import com.example.backend_challenge_tecnico_techforb.Dtos.Response.SensoresDto;
import com.example.backend_challenge_tecnico_techforb.Entitys.Planta;
import com.example.backend_challenge_tecnico_techforb.Entitys.Sensor;
import com.example.backend_challenge_tecnico_techforb.Entitys.Usuario;
import com.example.backend_challenge_tecnico_techforb.Repositorys.PlantaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlantaService {
    @Autowired
    private PlantaRepository repository;
    @Autowired
    private UserService usuarioService;
    @Autowired
    private SensorService sensorService;

    private List<String> paicesPermitidos=new ArrayList<String>(List.of("ARGENTINA","BRASIL","URUGUAY","PARAGUAY"));
    @Transactional
    public PlantaDtoResponse crear(PlantaDtoRequest planta) {
        try{
            if(paicesPermitidos.contains(planta.getPais())){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String email = authentication.getName(); // Este será el email del usuario
                Usuario user= usuarioService.getUsuarioByEmail(email);
                Planta nuevaPlanta=Planta.builder()
                                    .nombre(planta.getNombre())
                                    .pais(planta.getPais())
                                    .usuario(user)
                                    .build();
                Planta PCreada=repository.save(nuevaPlanta);
                sensorService.generarSensores(PCreada);
                return new PlantaDtoResponse(PCreada);
            }else{
                throw new RuntimeException("Pais No reconocido");
            }

        }catch (Exception e){
            throw e;
        }

    }

    public List<PlantaDtoResponse> getAll() {
//        List<PlantaDtoResponse> plantas=repository.findAll().stream().map(PlantaDtoResponse::new).toList() ;
        List<PlantaDtoResponse> plantas=repository.obtenerPlantasDto();
        return plantas;
    }

    public List<LecturasGeneralesDto> getAllLecturas() {
        List<LecturasGeneralesDto> lecturas= new ArrayList<>();
        LecturasGeneralesDto lecturasOk= new LecturasGeneralesDto("Lecturas Ok",repository.getAllLecturasOk());
        LecturasGeneralesDto alertasMedias= new LecturasGeneralesDto("Alertas medias",repository.getAllAlertasMedias());
        LecturasGeneralesDto alertasRojas= new LecturasGeneralesDto("Alertas rojas",repository.getAllAlertasRojas());
        LecturasGeneralesDto sensoresDesactivados= new LecturasGeneralesDto("Sensores deshabilitados",repository.getAllSensoresDesactivados());
        lecturas.add(lecturasOk);
        lecturas.add(alertasMedias);
        lecturas.add(alertasRojas);
        lecturas.add(sensoresDesactivados);
        return lecturas;
    }

    public List<SensoresDto> getDetalle(Long idPlanta) {
       List<SensoresDto> sensores= sensorService.getAllByPlanta(idPlanta).stream().map(SensoresDto::new).toList();
       return sensores;
    }
    @Transactional
    public PlantaDtoResponse editPlanta(Long id, PlantaDtoRequest planta)throws Exception {
        if(planta!=null&&id!=null){
            Planta entityPlanta= repository.findById(id).orElseThrow(()->new RuntimeException("Tenemos problamas para encontrar La planta"));
            entityPlanta.setPais(planta.getPais());
            entityPlanta.setNombre(planta.getNombre());
            repository.save(entityPlanta);
            return new PlantaDtoResponse(entityPlanta);
        }else {
            throw new Exception("Campos Enviados nulos");
        }

    }
    @Transactional
    public PlantaDtoResponse eliminarPlanta(Long id) {
        try {
            Planta entityPlanta= repository.findById(id).orElseThrow(()->new RuntimeException("Tenemos problamas para encontrar La planta"));
            repository.deleteById(id);
            return new PlantaDtoResponse(entityPlanta);
        }catch (Exception e){
            throw  e;
        }

    }
}
