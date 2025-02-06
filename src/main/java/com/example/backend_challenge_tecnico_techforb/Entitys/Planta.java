package com.example.backend_challenge_tecnico_techforb.Entitys;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "planta",uniqueConstraints = {@UniqueConstraint(columnNames={"nombre"})})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planta {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column
    private String pais;
    @Column
    private String nombre;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "planta",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Sensor> sensores;
}
