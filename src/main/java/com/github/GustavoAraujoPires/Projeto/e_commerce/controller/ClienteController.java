package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ClienteDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.mappers.ClienteMappers;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;
    private final ClienteMappers mappers;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody @Valid ClienteDTO dto){
        var cliente = service.salvar(mappers.toEntity(dto));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        var clientes = service.buscarTodosCliente();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        var cliente = service.buscarPorId(id);
        return ResponseEntity.ok().body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
