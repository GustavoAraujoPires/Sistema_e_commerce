package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.IdNaoEncontradoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ProdutoInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    public Produto salvarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new ProdutoInvalidoException("Nome do Pedido Iválido: ");
        }
        if (produto.getPreco() == null) {
            throw new ProdutoInvalidoException("Adicione um valor ao Produto");
        } else if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProdutoInvalidoException("Não é permitido esse valor ");
        }
        return repository.save(produto);
    }

    public Produto BuscarPedidoId(Long id){
        return repository.findById(id).orElseThrow(() -> new IdNaoEncontradoException("Id não encontrado !!"));
    }

    public List<Produto> buscarTodos(){
        return repository.findAll();
    }

    public void deletar(Long id){
        var produtos = repository.findById(id);
        if(produtos.isPresent()){
            repository.deleteById(id);
        }else {
            throw new IdNaoEncontradoException("Id não encontrado !!");
        }
    }
}
