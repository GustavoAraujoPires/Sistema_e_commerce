package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClienteDTO {
    @NotBlank(message = "Campo Obrigatório")
    @Size(max = 100, min = 2, message = "Nome fora do tamanho padrão")
    private String nome;
    @NotBlank(message = "Campo Obrigatório")
    @Email(message = "Email fora de padrão")
    private String email;

    public Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        return cliente;
    }
}
