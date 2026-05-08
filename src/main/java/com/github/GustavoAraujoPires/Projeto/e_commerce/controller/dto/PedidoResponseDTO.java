package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.StatusPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoResponseDTO {
    private Long id;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private Double valorTotal;
    private StatusPedido statusPedido;
}
