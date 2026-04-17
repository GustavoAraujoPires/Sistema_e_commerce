package com.github.GustavoAraujoPires.Projeto.e_commerce.dto;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {
    private String nome;
    private String email;

    public Cliente toEntity(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        return cliente;
    }
}
