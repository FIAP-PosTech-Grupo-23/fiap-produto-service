package com.fiap_produto_service.usecase;

import com.fiap_produto_service.gateway.ProdutoGateway;
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
