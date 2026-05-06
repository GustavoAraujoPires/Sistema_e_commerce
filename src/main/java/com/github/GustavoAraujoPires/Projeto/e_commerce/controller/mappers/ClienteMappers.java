package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.mappers;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ClienteDTO;
import com.github.GustavoAraujoPires.Projeto.e_commerce.model.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMappers {

    Cliente toEntity(ClienteDTO dto);

}
