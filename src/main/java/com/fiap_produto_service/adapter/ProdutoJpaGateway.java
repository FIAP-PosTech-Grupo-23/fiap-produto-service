package com.fiap_produto_service.adapter;

import com.fiap_produto_service.adapter.entity.ProdutoEntity;
import com.fiap_produto_service.adapter.exception.ProdutoNaoEncontradoException;
import com.fiap_produto_service.adapter.repository.ProdutoRepository;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public Long criar(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {

        ProdutoEntity produtoEntity = new ProdutoEntity(
                sku,
                nome,
                descricao,
                preco,
                dataCriacao,
                dataAtualizacao
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
    public Produto atualiza(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime dataAtualizacao) {

        ProdutoEntity produtoEntity = produtoRepository.findBySku(sku)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto nao encontrado"));

        produtoEntity.setNome(nome);
        produtoEntity.setDescricao(descricao);
        produtoEntity.setPreco(preco);
        produtoEntity.setAtualizadoEm(dataAtualizacao);

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
