package com.fiap_produto_service.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoControllerAdvice {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ExceptionDTO> handlerProdutoNaoEncontradoException(ProdutoNaoEncontradoException e){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ExceptionDTO(e.getMessage(), status.value()));
    }
}
