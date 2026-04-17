package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
public class Produto {
    private Long id;
    private String nome;
    private BigDecimal preco;

    @OneToMany
    private Cliente cliente;
}
