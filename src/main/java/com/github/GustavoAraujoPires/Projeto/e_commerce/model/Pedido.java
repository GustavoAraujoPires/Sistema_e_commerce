package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomePedido;
    private BigDecimal valorTotal;
    private StatusPedido statusPedido;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataEntrega;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    @ManyToMany
    @JoinTable(name = "produto_id")
    private List<Produto> listaProdutos;

}
