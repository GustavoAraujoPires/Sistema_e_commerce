package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.PedidoRequestDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.PedidoInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Pedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.StatusPedido;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ClienteRepository;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.PedidoRepository;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public Pedido salvarPedido(PedidoRequestDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ClienteInvalidoException("Cliente não encontrado por ID !!"));

        List<Produto> produtos = produtoRepository.findAllById(dto.getListaPedidoIds());

        if(produtos.size() != dto.getListaPedidoIds().size()){
            throw new PedidoInvalidoException("um ou mais Produtos não foram encontrado !!");
        }

        Pedido pedido =  new Pedido();
        pedido.setCliente(cliente);
        pedido.setListaProdutos(produtos);

       BigDecimal valorTotal = BigDecimal.ZERO;

        for (Produto p : produtos){
           if (p == null){
               throw new PedidoInvalidoException("Produto Invalido");
           }else if (p.getPreco() == null) {
            throw new PedidoInvalidoException("Cadastre o Produto com um Valor !");
        } else if (p.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new PedidoInvalidoException("O valor do pedido Tem que ser maior que zero !!");
        }
           valorTotal = valorTotal.add(p.getPreco());
        }
        pedido.setValorTotal(valorTotal);
        pedido.setStatusPedido(StatusPedido.CRIADO);
        return repository.save(pedido);
      }

      public Pedido pagarPedido(Long id){
           Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoInvalidoException("Pedido não encontrado !!"));
           if(pedido.getStatusPedido() != StatusPedido.CRIADO){
                throw new PedidoInvalidoException("Somente pedidos CRIADOS podem ser pagos !!");
           }
           pedido.setStatusPedido(StatusPedido.PAGO);
           if (pedido.getDataPagamento() != null){
               throw new PedidoInvalidoException("Pedido já está Pago !!");
           }
           pedido.setDataPagamento(LocalDateTime.now());
           return repository.save(pedido);
      }

      public Pedido entregarPedido(Long id){
          Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoInvalidoException("Pedido não encontrado !!"));
          if (pedido.getStatusPedido() != StatusPedido.PAGO){
              throw new PedidoInvalidoException("Somente pedidos Pagos pode ser Entregues !!");
          }
          pedido.setStatusPedido(StatusPedido.ENTREGUE);
          if (pedido.getDataEntrega() != null){
              throw new PedidoInvalidoException("Pedido já entregue !!");
          }
          pedido.setDataEntrega(LocalDateTime.now());
          return repository.save(pedido);
      }

      public Pedido cancelarPedido(Long id){
          Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoInvalidoException("Pedido não encontrado !!"));
          if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
              throw new PedidoInvalidoException("Pedido já está Cancelado !!");
          }else if(pedido.getStatusPedido() == StatusPedido.PAGO || pedido.getStatusPedido() == StatusPedido.ENTREGUE){
              throw new PedidoInvalidoException("Esse Pedido não pode ser Cancelado !!");
          }
          pedido.setStatusPedido(StatusPedido.CANCELADO);
          if (pedido.getDataCancelamento() != null){
              throw new PedidoInvalidoException("Pedido já cancelado !!");
          }
          pedido.setDataCancelamento(LocalDateTime.now());// pega a data e hora
          return repository.save(pedido);
      }

    }

