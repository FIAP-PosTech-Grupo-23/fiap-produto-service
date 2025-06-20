package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoDeletaUseCaseImpl implements ProdutoDeletaUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public void deleta(String sku) {

        produtoGateway.deleta(sku);

    }
}
