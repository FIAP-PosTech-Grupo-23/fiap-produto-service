package com.fiap_produto_service.usecase;

import com.fiap_produto_service.domain.Produto;
import com.fiap_produto_service.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoRecuperaPorSkusUseCaseImpl implements ProdutoRecuperaPorSkusUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public List<Produto> recuperaPorSkus(List<String> skus) {

        return produtoGateway.recuperaPorSkus(skus);

    }
}
