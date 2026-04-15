package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.PedidoIvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Pedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.StatusPedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        pedido.setStatusPedido(StatusPedido.CRIADO);
        return repository.save(pedido);
      }


      public Pedido pagarPedido(Long id){
           Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoIvalidoException("Pedido não encontrado"));
           if(pedido.getStatusPedido() != StatusPedido.CRIADO){
                throw new PedidoIvalidoException("Somente pedidos CRIADOS podem ser pagos");
           }
           pedido.setStatusPedido(StatusPedido.PAGO);
           return repository.save(pedido);
      }

      public Pedido entregarPedido(Long id){
          Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoIvalidoException("Pedido não encontrado"));
          if (pedido.getStatusPedido() != StatusPedido.PAGO){
              throw new PedidoIvalidoException("Somente pedidos Pago pode ser Entregues !!");
          }
          pedido.setStatusPedido(StatusPedido.ENTREGUE);
          return repository.save(pedido);
      }

      public Pedido cancelarPedido(Long id){
          Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoIvalidoException("Pedido não encontrado"));
          if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
              throw new PedidoIvalidoException("Pedido já está Cancelado !!");
          }else if(pedido.getStatusPedido() == StatusPedido.PAGO || pedido.getStatusPedido() == StatusPedido.ENTREGUE){
              throw new PedidoIvalidoException("Esse Pedido não pode ser Cancelado");
          } 
          pedido.setStatusPedido(StatusPedido.CANCELADO);
          return repository.save(pedido);
      }

    }

