package com.fiap_produto_service.controller.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoJson(

        Long id,

         String sku,

         String nome,

         String descricao,

         BigDecimal preco,

         LocalDateTime criadoEm,

         LocalDateTime atualizadoEm

) {
}
