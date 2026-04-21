package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.common;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroCampo;
import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroResposta;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.ClienteInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerMethodArgumentNotValidException (MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErro = fieldErrors.stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", listaErro);
    }

    @ExceptionHandler(ClienteInvalidoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handerClienteInvalidoException(ClienteInvalidoException e){
        List<ErroCampo> listaErro = List.of(new ErroCampo("email", e.getMessage()));
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação",listaErro);

    }
}
