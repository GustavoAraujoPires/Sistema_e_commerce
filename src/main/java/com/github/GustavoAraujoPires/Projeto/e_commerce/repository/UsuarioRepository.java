package com.github.GustavoAraujoPires.Projeto.e_commerce.repository;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Usuario findByLogin(String Login);
}
