package com.fiap_produto_service.usecase;

import com.fiap_produto_service.domain.Produto;
import com.fiap_produto_service.gateway.ProdutoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProdutoAtualizaUseCaseTest {

    @InjectMocks
    private ProdutoAtualizaUseCaseImpl produtoAtualizaUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveAtualizarProdutoComSucesso() {

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

        // Act
        produtoAtualizaUseCase.atualiza(produto);

        // Assert
        verify(produtoGateway, times(1)).atualiza(any(), any(), any(), any(), any());


    }

}
