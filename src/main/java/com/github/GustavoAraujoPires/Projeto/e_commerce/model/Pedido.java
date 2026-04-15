package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Pedido {
    private Long id;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private Double valorTotal;
    private StatusPedido statusPedido;
}
