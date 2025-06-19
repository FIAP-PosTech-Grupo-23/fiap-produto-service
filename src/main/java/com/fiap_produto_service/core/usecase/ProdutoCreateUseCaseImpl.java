package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoCreateUseCaseImpl implements ProdutoCreateUseCase{

    private final ProdutoGateway produtoGateway;

    @Override
    public Long criaProduto(Produto produto) {

        Long id = produtoGateway.criar(produto);

        return id;
    }
}
