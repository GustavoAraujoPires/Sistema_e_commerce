package com.github.GustavoAraujoPires.Projeto.e_commerce.repository;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{
}
