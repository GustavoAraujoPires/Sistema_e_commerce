package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.dto.ClienteDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<Cliente> salvar(@RequestBody ClienteDTO dto){
        var cliente = service.salvar(dto.toEntity(dto));
        return ResponseEntity.status(201).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        var clientes = service.buscarTodosCliente();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        var buscar = service.buscarPorId(id);
        return ResponseEntity.ok().body(buscar);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
