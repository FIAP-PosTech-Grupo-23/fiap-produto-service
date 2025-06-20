package com.fiap_produto_service.core;

import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCaseImpl;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkuUseCase;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkuUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProdutoRecuperaPorSkuUseCaseTest {

    @InjectMocks
    private ProdutoRecuperaPorSkuUseCaseImpl produtoRecuperaPorSkuUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveRecuperarProdutoComSucessoPorSku() {

        // Act
        produtoRecuperaPorSkuUseCase.recuperaPorSku("123");

        // Assert
        verify(produtoGateway, times(1)).recuperaPorSku("123");

    }

}
