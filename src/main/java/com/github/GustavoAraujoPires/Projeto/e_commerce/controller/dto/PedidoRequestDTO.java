package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {
    private Long clienteId;
    private List<Long> produtosIds;
}
