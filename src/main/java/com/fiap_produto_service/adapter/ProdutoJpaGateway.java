package com.fiap_produto_service.adapter;

import com.fiap_produto_service.adapter.entity.ProdutoEntity;
import com.fiap_produto_service.adapter.exception.ProdutoNaoEncontradoException;
import com.fiap_produto_service.adapter.repository.ProdutoRepository;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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

        ProdutoEntity produtoEntity = produtoRepository.findBySku(sku)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto nao encontrado"));

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

    @Override
    @Transactional
    public Produto atualiza(Produto produto) {

        ProdutoEntity produtoEntity = produtoRepository.findBySku(produto.getSku())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto nao encontrado"));

        produtoEntity.setNome(produto.getNome());
        produtoEntity.setDescricao(produto.getDescricao());
        produtoEntity.setPreco(produto.getPreco());
        produtoEntity.setAtualizadoEm(LocalDateTime.now());

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
