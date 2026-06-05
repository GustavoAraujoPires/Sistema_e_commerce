package com.github.GustavoAraujoPires.Projeto.e_commerce.controller;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.UsuarioDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.mappers.UsuarioMappers;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Usuario;
import com.github.GustavoAraujoPires.Projeto.e_commerce.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioMappers mappers;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar (@RequestBody UsuarioDTO dto){
        var usuario = mappers.toEntity(dto);
        service.salvar(usuario);
    }

    @GetMapping("/login")
    public Usuario buscarPorLogin(@RequestBody String login){
        return service.buscarPorLogin(login);
    }
}
