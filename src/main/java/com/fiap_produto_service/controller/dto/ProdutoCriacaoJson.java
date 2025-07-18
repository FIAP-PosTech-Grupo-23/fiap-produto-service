package com.fiap_produto_service.controller.dto;

import java.math.BigDecimal;

public record ProdutoCriacaoJson(
        String nome,
        String descricao,
        BigDecimal preco
) {
}
