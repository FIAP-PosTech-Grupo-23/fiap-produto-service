package com.fiap_produto_service.adapter;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkuUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoCriaUseCase produtoCriaUseCase;
    private final ProdutoRecuperaPorSkuUseCase produtoRecuperaPorSkuUseCase;

    @PostMapping
    public ResponseEntity<Long> criaProduto(@RequestBody ProdutoCriacaoJson produtoCriacaoJson) {
        Long id = produtoCriaUseCase.criaProduto(criaProdutoDomain(produtoCriacaoJson));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProdutoJson> recuperaPorSku(@PathVariable String sku) {
        return ResponseEntity.ok(criaProdutoJson(produtoRecuperaPorSkuUseCase.recuperaPorSku(sku)));
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

    private Produto criaProdutoDomain(ProdutoCriacaoJson produtoCriacaoJson) {
        return new Produto(produtoCriacaoJson.nome(), produtoCriacaoJson.descricao(), produtoCriacaoJson.preco());
    }

}
