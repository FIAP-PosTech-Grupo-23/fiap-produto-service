package com.fiap_produto_service.core.gateway;

import com.fiap_produto_service.core.domain.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ProdutoGateway {
    Long criar(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao);

    List<Produto> recuperaPorSkus(List<String> skus);

    Produto atualiza(String sku, String nome, String descrição, BigDecimal preco, LocalDateTime dataAtualizacao);

    void deleta(String sku);
}
