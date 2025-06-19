package com.fiap_produto_service.adapter;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.usecase.ProdutoCreateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoCreateUseCase produtoCreateUseCase;

    @PostMapping
    public ResponseEntity<Long> criaProduto(@RequestBody ProdutoJson produtoJson) {
        Long id = produtoCreateUseCase.criaProduto(criaProdutoDomain(produtoJson));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    private Produto criaProdutoDomain(ProdutoJson produtoJson) {
        return new Produto(produtoJson.nome(), produtoJson.descricao(), produtoJson.preco());
    }

}
