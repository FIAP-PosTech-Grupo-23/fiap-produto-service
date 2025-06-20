package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;

import java.util.List;

public interface ProdutoRecuperaPorSkusUseCase {
    List<Produto> recuperaPorSkus(List<String> sku);
}
