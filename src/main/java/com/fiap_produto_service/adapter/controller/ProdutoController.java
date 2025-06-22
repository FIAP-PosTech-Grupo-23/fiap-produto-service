package com.fiap_produto_service.adapter.controller;

import com.fiap_produto_service.adapter.dto.ProdutoCriacaoJson;
import com.fiap_produto_service.adapter.dto.ProdutoJson;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.usecase.ProdutoAtualizaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoDeletaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkusUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoCriaUseCase produtoCriaUseCase;
    private final ProdutoRecuperaPorSkusUseCase produtoRecuperaPorSkusUseCase;
    private final ProdutoAtualizaUseCase produtoAtualizaUseCase;
    private final ProdutoDeletaUseCase produtoDeletaUseCase;

    @PostMapping
    public ResponseEntity<Long> criaProduto(@RequestBody ProdutoCriacaoJson produtoCriacaoJson) {
        Long id = produtoCriaUseCase.criaProduto(criaProdutoDomain(produtoCriacaoJson));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/listar")
    public ResponseEntity<List<ProdutoJson>> recuperaProdutosPorSkus(@RequestBody List<String> skus) {
        return ResponseEntity.ok(criaProdutoJson(produtoRecuperaPorSkusUseCase.recuperaPorSkus(skus)));
    }

    @PatchMapping()
    public ResponseEntity<ProdutoJson> atualizaProduto(@RequestBody ProdutoJson produtoJson) {
        return ResponseEntity.ok()
                .body(criaProdutoJson(produtoAtualizaUseCase.atualiza(atualizaProdutoDomain(produtoJson))));
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deletaProduto(@PathVariable String sku) {
        produtoDeletaUseCase.deleta(sku);
        return ResponseEntity.noContent().build();
    }

    private Produto atualizaProdutoDomain(ProdutoJson produtoJson) {
        return new Produto(produtoJson.sku(),
                produtoJson.nome(),
                produtoJson.descricao(),
                produtoJson.preco());
    }

    private ProdutoJson criaProdutoJson(Produto produto) {
        return new ProdutoJson(
                produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCriadoEm(),
                produto.getAtualizadoEm()
        );
    }

    private List<ProdutoJson> criaProdutoJson(List<Produto> produtos) {

        return produtos.stream().map(produto -> new ProdutoJson(
                produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCriadoEm(),
                produto.getAtualizadoEm())).toList();

    }



    private Produto criaProdutoDomain(ProdutoCriacaoJson produtoCriacaoJson) {
        return new Produto(produtoCriacaoJson.nome(), produtoCriacaoJson.descricao(), produtoCriacaoJson.preco());
    }

}
