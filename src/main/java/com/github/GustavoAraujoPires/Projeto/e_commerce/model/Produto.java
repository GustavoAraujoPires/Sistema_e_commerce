package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Produto {
    private UUID id;
    private String nome;
    private BigDecimal preco;
}
