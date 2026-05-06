package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("produtos")
public class ProdutosController {

    private final ProdutoService service;

    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto){
        service.salvarProduto(produto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        var produtos = service.BuscarPedidoId(id);
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> buscarTodos(){
        var produto = service.buscarTodos();
       return ResponseEntity.ok().body(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}


