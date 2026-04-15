package com.github.GustavoAraujoPires.Projeto.e_commerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Pedido {
    private Long id;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private Double valorTotal;
    private StatusPedido statusPedido;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataEntrega;
}
