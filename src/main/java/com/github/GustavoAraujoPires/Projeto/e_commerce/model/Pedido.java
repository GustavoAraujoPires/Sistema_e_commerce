package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePedido;
    private BigDecimal valorTotal;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToMany
    @Transient
    @JoinTable(name = "produto_id")
    private List<Produto> listaProdutos;

}
