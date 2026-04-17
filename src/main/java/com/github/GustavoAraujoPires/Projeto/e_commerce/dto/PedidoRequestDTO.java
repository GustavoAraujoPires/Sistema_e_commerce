package com.github.GustavoAraujoPires.Projeto.e_commerce.dto;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {
    private Long clienteId;
    private List<Long> listaPedidoIds;
}
