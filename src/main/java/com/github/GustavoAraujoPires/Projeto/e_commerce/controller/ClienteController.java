package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.dto.ClienteDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService service;

    @PostMapping
    public ResponseEntity<ClienteDTO> salvar(@RequestBody ClienteDTO dto){
        var cliente = service.salvar(dto.toEntity(dto));
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos(){
        var clientes = service.buscarTodosCliente();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        var cliente = service.buscarPorId(id);
        return  ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){
        if (id == null){
            throw new ClienteInvalidoException("Id Não encontrado !!! ");
        }
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
