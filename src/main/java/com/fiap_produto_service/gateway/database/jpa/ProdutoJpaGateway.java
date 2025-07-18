package com.fiap_produto_service.gateway.database.jpa;

import com.fiap_produto_service.gateway.database.jpa.entity.ProdutoEntity;
import com.fiap_produto_service.exception.ProdutoNaoEncontradoException;
import com.fiap_produto_service.gateway.database.jpa.repository.ProdutoRepository;
import com.fiap_produto_service.domain.Produto;
import com.fiap_produto_service.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoJpaGateway implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;

    @Transactional
    @Override
    public Produto criar(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao) {

        ProdutoEntity produtoEntity = new ProdutoEntity(
                sku,
                nome,
                descricao,
                preco,
                dataCriacao,
                dataAtualizacao
        );

        ProdutoEntity produtoEntityRetorno = produtoRepository.save(produtoEntity);

        return new Produto(
                produtoEntityRetorno.getId(),
                produtoEntityRetorno.getSku(),
                produtoEntityRetorno.getNome(),
                produtoEntityRetorno.getDescricao(),
                produtoEntityRetorno.getPreco(),
                produtoEntityRetorno.getCriadoEm(),
                produtoEntityRetorno.getAtualizadoEm()
        );

    }

    @Override
    public List<Produto> recuperaPorSkus(List<String> skus) {

        List<ProdutoEntity> produtosEntity = produtoRepository.findBySkuIn(skus);

        return produtosEntity.stream()
                .map(produtoEntity ->
                        new Produto(
                                produtoEntity.getId(),
                                produtoEntity.getSku(),
                                produtoEntity.getNome(),
                                produtoEntity.getDescricao(),
                                produtoEntity.getPreco(),
                                produtoEntity.getCriadoEm(),
                                produtoEntity.getAtualizadoEm()
                        )
                ).toList();

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

    @Override
    @Transactional
    public void deleta(String sku) {

        produtoRepository.deleteBySku(sku);

    }
}
