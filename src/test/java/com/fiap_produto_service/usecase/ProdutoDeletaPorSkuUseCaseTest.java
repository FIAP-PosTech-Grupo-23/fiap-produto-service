package com.fiap_produto_service.usecase;

import com.fiap_produto_service.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProdutoDeletaPorSkuUseCaseTest {

    @InjectMocks
    private ProdutoDeletaUseCaseImpl produtoDeletaUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveDeletarProdutoComSucessoPorSku() {

        // Act
        produtoDeletaUseCase.deleta("123");

        // Assert
        verify(produtoGateway, times(1)).deleta("123");

    }

}
