package com.fiap_produto_service.usecase;

import com.fiap_produto_service.domain.Produto;
import com.fiap_produto_service.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdutoAtualizaUseCaseImpl implements ProdutoAtualizaUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public Produto atualiza(Produto produto) {

        String sku = produto.getSku();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();
        BigDecimal preco = produto.getPreco();
        LocalDateTime dataAtualizacao = LocalDateTime.now();

        return produtoGateway.atualiza(sku, nome, descricao, preco, dataAtualizacao);

    }
}
