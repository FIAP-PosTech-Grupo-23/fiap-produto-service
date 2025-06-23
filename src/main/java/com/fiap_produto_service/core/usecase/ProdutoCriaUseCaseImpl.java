package com.fiap_produto_service.core.usecase;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProdutoCriaUseCaseImpl implements ProdutoCriaUseCase {

    private final ProdutoGateway produtoGateway;
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public Long criaProduto(Produto produto) {

        String sku = geraSku();
        String nome = produto.getNome();
        String descricao = produto.getDescricao();
        BigDecimal preco = produto.getPreco();
        LocalDateTime criadoEm = LocalDateTime.now();
        LocalDateTime atualizadoEm = LocalDateTime.now();

        return produtoGateway.criar(sku, nome, descricao, preco, criadoEm, atualizadoEm);

    }

    private String geraSku() {
        StringBuilder sku = new StringBuilder("PROD-");
        for (int i = 0; i < 6; i++) {
            sku.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
        }
        return sku.toString();
    }
}
