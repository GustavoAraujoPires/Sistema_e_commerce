package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pedido {
    private UUID id;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private BigDecimal valorTotal;
    private status statusPedido;
}
