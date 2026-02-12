package com.gabriel.horta.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "produto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeComum; // Ex: Jiboia, Samambaia

    private String nomeCientifico; // Ex: Epipremnum aureum

    private String categoria; // Planta, Vaso, Ferramenta

    private Double precoVenda;

    private Integer estoque;

    private String luminosidade; // Sol, Meia-sombra, Sombra

    private Boolean petFriendly;
}