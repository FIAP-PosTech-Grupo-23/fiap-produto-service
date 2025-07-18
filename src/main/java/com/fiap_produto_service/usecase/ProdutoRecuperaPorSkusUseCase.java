package com.fiap_produto_service.usecase;

import com.fiap_produto_service.domain.Produto;

import java.util.List;

public interface ProdutoRecuperaPorSkusUseCase {
    List<Produto> recuperaPorSkus(List<String> sku);
}
