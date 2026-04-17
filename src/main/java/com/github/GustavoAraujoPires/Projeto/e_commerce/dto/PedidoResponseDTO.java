package com.github.GustavoAraujoPires.Projeto.e_commerce.dto;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Pedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.StatusPedido;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Long id;
    private Cliente cliente;
    private List<Produto> listaProdutos;
    private Double valorTotal;
    private StatusPedido statusPedido;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataEntrega;

    public Pedido toEntity (){
        Pedido pedido = new Pedido();
        pedido.setCliente(this.getCliente());
        pedido.setListaProdutos(this.getListaProdutos());
        pedido.setValorTotal(this.getValorTotal());
        pedido.setStatusPedido(this.getStatusPedido());
        return pedido;
    }

    public Pedido fromEntity (){
       Produto produto = new Produto();
       produto.getPreco();
       produto.getNome();
       return 
    }

}
