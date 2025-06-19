package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;

public interface ProdutoRecuperaPorSkuUseCase {
    Produto recuperaPorSku(String sku);
}
