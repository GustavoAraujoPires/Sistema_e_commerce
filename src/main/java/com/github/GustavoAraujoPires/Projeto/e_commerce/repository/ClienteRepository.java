package com.github.GustavoAraujoPires.Projeto.e_commerce.repository;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    boolean existsByEmail(String email);
}
