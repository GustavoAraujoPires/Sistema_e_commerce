package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Cliente {
    private UUID id;
    private String nome;
    private String email;
}
