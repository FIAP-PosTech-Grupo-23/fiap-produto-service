package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdutoCriaUseCaseImpl implements ProdutoCriaUseCase {

    private final ProdutoGateway produtoGateway;

    @Override
    public Long criaProduto(Produto produto) {

        String sku = produto.getSku();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();
        BigDecimal preco = produto.getPreco();
        LocalDateTime criadoEm = LocalDateTime.now();
        LocalDateTime atualizadoEm = LocalDateTime.now();

        return produtoGateway.criar(sku, nome, descricao, preco, criadoEm, atualizadoEm);

    }
}
