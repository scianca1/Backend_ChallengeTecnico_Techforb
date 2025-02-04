package com.example.backend_challenge_tecnico_techforb.Entitys;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "planta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planta {
    @Id
    private Long id;
    @Column
    private String pais;
    @Column
    private String nombre;
    @ManyToOne
    private Usuario usuario;
}
