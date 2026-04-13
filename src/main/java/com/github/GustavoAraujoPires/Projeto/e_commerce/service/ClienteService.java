package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente){
        if(cliente.getNome() == null || cliente.getNome().isBlank()){ // é um método do Java (String) que verifica se o texto está:  Vazio OU só com espaços
            throw new ClienteInalidoException("Nome do Cliente Invalido");
        }
        if(cliente.getEmail() == null || !cliente.getEmail().contains("@")){
            throw new ClienteInalidoException("Email inválido");
        }
        if (repository.existsByEmail(cliente.getEmail())){
            throw new ClienteInalidoException("Email já cadastrado !!");
        }
        System.out.println("Cliente cadastrado: "+ cliente.getEmail());
        return repository.save(cliente);
    }

    public List<Cliente> buscarTodosCliente(){
        return repository.findAll();
    }
    public Cliente buscarPorId(UUID id){
        return repository.findById(id).orElse(null);
    }

    public void deletarCliente(UUID id){
        if (id == null){
            throw new ClienteInalidoException("Id Iválido: ");
        }
        repository.deleteById(id);
    }
}
