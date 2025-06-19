package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoRecuperaPorSkuUseCaseImpl implements ProdutoRecuperaPorSkuUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public Produto recuperaPorSku(String sku) {

        return produtoGateway.recuperaPorSku(sku);

    }
}
