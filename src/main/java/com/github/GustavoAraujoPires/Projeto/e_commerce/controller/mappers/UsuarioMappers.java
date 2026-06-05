package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.mappers;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.UsuarioDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMappers {

    Usuario toEntity(UsuarioDTO dto);
    UsuarioDTO toDTO(Usuario usuario);
}
