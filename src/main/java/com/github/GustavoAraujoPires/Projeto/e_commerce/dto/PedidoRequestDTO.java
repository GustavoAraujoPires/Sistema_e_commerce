package com.github.GustavoAraujoPires.Projeto.e_commerce.dto;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PedidoRequestDTO {
    private Long clienteId;
    private List<Long> listaPedidoIds;
}
