package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import java.util.List;

public record UsuarioDTO(String login, String senha, List<String> roles) {
}
