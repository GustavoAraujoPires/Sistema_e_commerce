package com.github.GustavoAraujoPires.Projeto.e_commerce.controller.common;

import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroCampo;
import com.github.GustavoAraujoPires.Projeto.e_commerce.controller.dto.ErroResposta;
import com.github.GustavoAraujoPires.Projeto.e_commerce.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErroResposta handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErro = fieldErrors.stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ErroResposta(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Erro de validação", listaErro);
    }

    @ExceptionHandler(ClienteInvalidoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handerClienteInvalidoException(ClienteInvalidoException e) {
        return  ErroResposta.clienteNaoEncontrado(e.getMessage());
    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handerIdInvalidoException(IdNaoEncontradoException e) {
        return  ErroResposta.idNaoEncontrado(e.getMessage());
    }

    @ExceptionHandler(ProdutoInvalidoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResposta handerProdutosInvalidoException(ProdutoInvalidoException e) {
        return ErroResposta.produtoNaoEncontrado(e.getMessage());
    }
    @ExceptionHandler(PedidoInvalidoException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErroResposta handerPedidoInvalidoException(PedidoInvalidoException e) {
        return ErroResposta.naoPermitido(e.getMessage());
    }
    @ExceptionHandler(PagamentoInvalidoException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ErroResposta handerPagamentoInvalido(PagamentoInvalidoException e){
        return ErroResposta.PagamentoInvalido(e.getMessage());
    }
    @ExceptionHandler(PedidoEntregueException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErroResposta handerPedidoJaEntregue(PedidoEntregueException e){
        return ErroResposta.naoAltorizado(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handerErroNaoTratado(RuntimeException e){
    return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado. Entre em contato com a administração.", List.of());
   }
}

