package com.fiap_produto_service.adapter;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public Long criar(Produto produto) {

        ProdutoEntity produtoEntity = new ProdutoEntity(
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCriadoEm(),
                produto.getAtualizadoEm()
        );

        return produtoRepository.save(produtoEntity).getId();

    }

    @Override
    public Produto recuperaPorSku(String sku) {

        ProdutoEntity produtoEntity = produtoRepository.findBySku(sku);

        return new Produto(
                produtoEntity.getId(),
                produtoEntity.getSku(),
                produtoEntity.getNome(),
                produtoEntity.getDescricao(),
                produtoEntity.getPreco(),
                produtoEntity.getCriadoEm(),
                produtoEntity.getAtualizadoEm()
        );

    }
}
