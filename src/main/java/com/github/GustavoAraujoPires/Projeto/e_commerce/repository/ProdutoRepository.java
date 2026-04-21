package com.github.GustavoAraujoPires.Projeto.e_commerce.repository;

import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
