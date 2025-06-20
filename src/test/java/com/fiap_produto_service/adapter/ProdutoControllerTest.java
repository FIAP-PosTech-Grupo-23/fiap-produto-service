package com.fiap_produto_service.adapter;

import com.fiap_produto_service.adapter.controller.ProdutoController;
import com.fiap_produto_service.adapter.dto.ProdutoCriacaoJson;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkuUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private ProdutoCriaUseCase produtoCriaUseCase;

    @Mock
    private ProdutoRecuperaPorSkuUseCase produtoRecuperaPorSkuUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveCriarProdutoComSucesso() {

        // Arrange
        ProdutoCriacaoJson json = new ProdutoCriacaoJson("Caneta", "Azul", new BigDecimal("3.00"));
        when(produtoCriaUseCase.criaProduto(any(Produto.class))).thenReturn(10L);

        // Act
        ResponseEntity<Long> response = produtoController.criaProduto(json);

        // Assert
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(response.getBody()).isEqualTo(10L);
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
        when(produtoCriaUseCase.criaProduto(any(Produto.class))).thenReturn(10L);
        when(produtoRecuperaPorSkuUseCase.recuperaPorSku(any())).thenReturn(produto);

        // Act
        produtoController.recuperaPorSku("123");

        // Assert
        verify(produtoRecuperaPorSkuUseCase, times(1)).recuperaPorSku("123");

    }



}
