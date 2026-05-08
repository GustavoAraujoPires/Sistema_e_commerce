package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.PedidoRequestDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.*;
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
                .orElseThrow(() ->  new ClienteInvalidoException("Id não encontrado !!"));

        if (dto.getProdutosIds().isEmpty()) {
            throw new PedidoInvalidoException("Lista de pedidos está fazia !!");
        }


        List<Produto> produtos = produtoRepository.findAllById(dto.getProdutosIds());

        if (produtos.size() != dto.getProdutosIds().size()) {
            throw new PedidoInvalidoException("um ou mais Produtos não foram encontrado !!");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setListaProdutos(produtos);

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (Produto p : produtos) {
            if (p == null) {
                throw new PedidoInvalidoException("Produto Invalido");
            } else if (p.getPreco() == null) {
                throw new PedidoInvalidoException("Cadastre o Produto com um Valor !");
            } else if (p.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
                throw new PedidoInvalidoException("O valor do pedido Tem que ser maior que zero !!");
            }
            valorTotal = valorTotal.add(p.getPreco());
        }

        if (produtos.size() == 1) {
            // Se tiver apenas 1 produto, usa o nome dele
            pedido.setNomePedido(produtos.get(0).getProdutoPrincipal());
        } else {
            // Se tiver vários produtos, pega o nome do primeiro e adiciona "+X"
            String primeiroProduto = produtos.get(0).getProdutoPrincipal();
            int restante = produtos.size() - 1;
            pedido.setNomePedido(primeiroProduto + " +" + restante);
        }

        pedido.setValorTotal(valorTotal);
        pedido.setStatusPedido(StatusPedido.CRIADO);
        return repository.save(pedido);
    }

    public Pedido pagarPedido(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new PagamentoInvalidoException("Somente pedidos CRIADOS podem ser pagos !!"));
        pedido.setStatusPedido(StatusPedido.PAGO);
        if (pedido.getDataPagamento() != null) {
            throw new PagamentoInvalidoException("Pedido já está Pago !!");
        }
        pedido.setDataPagamento(LocalDateTime.now());
        return repository.save(pedido);
    }

    public Pedido entregarPedido(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoInvalidoException("Somente pedidos Pagos pode ser Entregues !!"));
        pedido.setStatusPedido(StatusPedido.ENTREGUE);
        if (pedido.getDataEntrega() != null) {
            throw new PedidoEntregueException("Pedido já foi entregue !!");
        }
        pedido.setDataEntrega(LocalDateTime.now());
        return repository.save(pedido);
    }

    public Pedido cancelarPedido(Long id) {
        Pedido pedido = repository.findById(id).orElseThrow(() -> new PedidoInvalidoException("Pedido não encontrado !!"));
        if (pedido.getStatusPedido() == StatusPedido.CANCELADO) {
            throw new PedidoInvalidoException("O pedido já está cancelado. !!");
        } else if (pedido.getStatusPedido() == StatusPedido.PAGO || pedido.getStatusPedido() == StatusPedido.ENTREGUE) {
            throw new PedidoInvalidoException("Este pedido não pode ser cancelado, pois já foi entregue. !!");
        }
        pedido.setStatusPedido(StatusPedido.CANCELADO);
        if (pedido.getDataCancelamento() != null) {
            throw new PedidoInvalidoException("O pedido já está cancelado. !!");
        }
        pedido.setDataCancelamento(LocalDateTime.now());// pega a data e hora
        return repository.save(pedido);
    }

    public void deletar(Long id) {
        var resultado = repository.findById(id);
        if (resultado.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IdNaoEncontradoException("Id não encontrado !!");
        }
    }

    public List<Pedido>buscarTodos(){
        return repository.findAll();
    }

}

