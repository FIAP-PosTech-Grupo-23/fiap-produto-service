package com.fiap_produto_service.core.gateway;

import com.fiap_produto_service.core.domain.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ProdutoGateway {
    Long criar(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao);

    Produto recuperaPorSku(String sku);

    Produto atualiza(String sku, String nome, String descrição, BigDecimal preco, LocalDateTime dataAtualizacao);
}
