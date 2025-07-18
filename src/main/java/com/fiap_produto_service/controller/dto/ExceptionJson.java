package com.fiap_produto_service.controller.dto;

public record ExceptionJson(
        String mensagem,
        int status
) {
}
