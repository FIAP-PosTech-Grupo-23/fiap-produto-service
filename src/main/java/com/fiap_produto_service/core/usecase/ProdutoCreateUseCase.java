package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;

public interface ProdutoCreateUseCase {
    Long criaProduto(Produto produto);
}
