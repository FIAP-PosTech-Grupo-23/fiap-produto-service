package com.fiap_produto_service.adapter;

import java.math.BigDecimal;

public record ProdutoJson(
        String nome,
        String descricao,
        BigDecimal preco
) {
}
