package com.fiap_produto_service.core.gateway;

import com.fiap_produto_service.core.domain.Produto;

import java.util.UUID;

public interface ProdutoGateway {
    Long criar(Produto produto);

    Produto recuperaPorSku(String sku);
}
