package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.PedidoIvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Pedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final ClienteService clienteService;
    private final ProdutoService produtoService;

    public Pedido salvarPedido(Pedido pedido) {
        if (pedido.getCliente() == null) {
            throw new PedidoIvalidoException("Cliente não encontrado !");
        } else if (pedido.getListaProdutos() == null) {
            throw new PedidoIvalidoException("Lista de produtos não encontrada !");
        }else if (pedido.getListaProdutos().isEmpty()) {
            throw new PedidoIvalidoException("Nem um Pedido encontrado! ");
        }
        double valorTotal = 0;
        for (int i = 0; i < pedido.getListaProdutos().size(); i++) {
           Produto produto = pedido.getListaProdutos().get(i);
           if (produto == null){
               throw new PedidoIvalidoException("Produto Invalido");
           }else if (produto.getPreco() == null) {
            throw new PedidoIvalidoException("Cadastre o Produto com um Valor !");
        } else if (produto.getPreco() <= 0) {
            throw new PedidoIvalidoException("O valor do pedido Tem que ser maior que zero !!");
        }
           valorTotal += produto.getPreco();
        }
        pedido.setValorTotal(valorTotal);
        return repository.save(pedido);
      }


      public List<Pedido> buscarTodosPedidos(){
        return repository.findAll();
      }

    }

