package com.example.backend_challenge_tecnico_techforb.Entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Sensor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Planta planta;
    @Column
    private Long lecturasOK;
    @Column
    private Long alertasMedias;
    @Column
    private Long alertasRojas;
    @Column
    private boolean habilitado;
    @Column
    private String nombre;
}
