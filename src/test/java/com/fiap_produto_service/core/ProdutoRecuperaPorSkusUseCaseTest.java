package com.fiap_produto_service.core;

import com.fiap_produto_service.core.gateway.ProdutoGateway;
import com.fiap_produto_service.core.usecase.ProdutoRecuperaPorSkusUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProdutoRecuperaPorSkusUseCaseTest {

    @InjectMocks
    private ProdutoRecuperaPorSkusUseCaseImpl produtoRecuperaPorSkuUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveRecuperarProdutoComSucessoPorSku() {

        // Act
        produtoRecuperaPorSkuUseCase.recuperaPorSkus(List.of("123"));

        // Assert
        verify(produtoGateway, times(1)).recuperaPorSkus(List.of("123"));

    }

}
