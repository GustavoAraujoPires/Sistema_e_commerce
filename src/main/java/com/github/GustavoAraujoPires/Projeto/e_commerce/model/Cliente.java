package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Cliente {
    private Long id;
    private String nome;
    private String email;

    @ManyToOne
    private List<Produto> produto;
}
