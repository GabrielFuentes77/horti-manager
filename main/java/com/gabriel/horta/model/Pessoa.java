package com.gabriel.horta.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Campo obrigatório [cite: 33]
    private String nome;

    @Column(nullable = false, unique = true) // CPF/CNPJ obrigatório [cite: 33]
    private String cpfCnpj;

    private String endereco;
    private String numero;
    private String bairro;
    private String cep;
    private String telefone;
    private String email;

    @ManyToOne // Muitas pessoas podem morar em uma cidade
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    private Boolean ehCliente = true;
    private Boolean ehFornecedor = false;
}