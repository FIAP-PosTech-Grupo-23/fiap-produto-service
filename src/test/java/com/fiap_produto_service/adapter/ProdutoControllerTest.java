package com.fiap_produto_service.adapter;

import com.fiap_produto_service.adapter.controller.ProdutoController;
import com.fiap_produto_service.adapter.dto.ProdutoCriacaoJson;
import com.fiap_produto_service.adapter.dto.ProdutoJson;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.usecase.ProdutoAtualizaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoDeletaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkusUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoCriaUseCase produtoCriaUseCase;

    @Mock
    private ProdutoRecuperaPorSkusUseCase produtoRecuperaPorSkusUseCase;

    @Mock
    private ProdutoAtualizaUseCase produtoAtualizaUseCase;

    @Mock
    private ProdutoDeletaUseCase produtoDeletaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeveCriarProdutoComSucesso() {

        // Arrange
        ProdutoCriacaoJson json = new ProdutoCriacaoJson("Caneta", "Azul", new BigDecimal("3.00"));
        when(produtoCriaUseCase.criaProduto(any(Produto.class))).thenReturn("123");

        // Act
        ResponseEntity<String> response = produtoController.criaProduto(json);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo("123");
        verify(produtoCriaUseCase).criaProduto(any(Produto.class));

    }

    @Test
    void testDeveRecuperarProdutoPorSku() {

        // Arrange
        Produto produto = new Produto(
                1L,
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(produtoCriaUseCase.criaProduto(any(Produto.class))).thenReturn("123");
        when(produtoRecuperaPorSkusUseCase.recuperaPorSkus(any())).thenReturn(List.of(produto));

        // Act
        produtoController.recuperaProdutosPorSkus(List.of("123"));

        // Assert
        verify(produtoRecuperaPorSkusUseCase, times(1)).recuperaPorSkus(List.of("123"));

    }

    @Test
    void testDeveAtualizarProduto() {

        // Arrange
        ProdutoJson produtoJson = new ProdutoJson(
                1L,
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now()

        );

        Produto produto = new Produto(
                1L,
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        when(produtoAtualizaUseCase.atualiza(any(Produto.class))).thenReturn(produto);

        // Act
        produtoController.atualizaProduto(produtoJson);

        // Assert
        verify(produtoAtualizaUseCase).atualiza(any(Produto.class));

    }

    @Test
    void testDeletarProdutoPorSku() {

        // Act
        produtoController.deletaProduto("123");

        // Assert
        verify(produtoDeletaUseCase, times(1)).deleta("123");

    }



}
