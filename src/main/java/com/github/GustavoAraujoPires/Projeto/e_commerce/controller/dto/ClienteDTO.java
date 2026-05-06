package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotBlank(message = "Campo Obrigatório")
    @Size(max = 100, min = 2)
    private String nome;
    @NotBlank(message = "Campo Obrigatório")
    @Email
    private String email;
}
