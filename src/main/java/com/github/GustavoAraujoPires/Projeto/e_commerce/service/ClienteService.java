package com.github.GustavoAraujoPires.Projeto.e_commerce.service;

import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInvalidoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteIdNaoEncontradoException;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ClienteRepository;
import com.github.GustavoAraujoPires.Projeto.e_commerce.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
    private final ProdutoRepository produtoRepository;

    public Cliente salvar(Cliente cliente){
        if (repository.existsByEmail(cliente.getEmail())){
            throw new ClienteInvalidoException("Email já cadastrado !!");
        }
        return repository.save(cliente);
    }

    public List<Cliente> buscarTodosCliente(){
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return repository.findById(id).orElseThrow(() -> new ClienteIdNaoEncontradoException("Id não encontrado !!"));
    }

    public void deletarCliente(Long id){

       var cliente = repository.findById(id);
        if (cliente.isPresent()){
            repository.deleteById(id);
        }
        else {
            throw new ClienteIdNaoEncontradoException("Id não encontrado !!");
        }
    }
}
