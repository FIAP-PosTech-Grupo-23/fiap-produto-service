package com.fiap_produto_service.adapter.config;

import com.fiap_produto_service.adapter.dto.ExceptionJson;
import com.fiap_produto_service.adapter.exception.ProdutoNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProdutoControllerAdvice {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ExceptionJson> handlerProdutoNaoEncontradoException(ProdutoNaoEncontradoException e){
        var status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status.value()).body(new ExceptionJson(e.getMessage(), status.value()));
    }
}
