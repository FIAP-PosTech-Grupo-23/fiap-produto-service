package com.fiap_produto_service.core;

import com.fiap_produto_service.adapter.dto.ProdutoCriacaoJson;
import com.fiap_produto_service.core.domain.Produto;
import com.fiap_produto_service.core.gateway.ProdutoGateway;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCase;
import com.fiap_produto_service.core.usecase.ProdutoCriaUseCaseImpl;
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

class ProdutoCriaUseCaseTest {

    @InjectMocks
    private ProdutoCriaUseCaseImpl produtoCriaUseCase;

    @Mock
    private ProdutoGateway produtoGateway;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testDeveCriarProdutoComSucesso() {

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
        produtoCriaUseCase.criaProduto(produto);

        // Assert
        verify(produtoGateway, times(1)).criar(produto);


    }

}
