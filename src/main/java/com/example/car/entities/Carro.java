package com.example.car.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carros")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private Integer ano;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
}


