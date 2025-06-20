package com.fiap_produto_service.adapter;

import com.fiap_produto_service.adapter.entity.ProdutoEntity;
import com.fiap_produto_service.adapter.repository.ProdutoRepository;
import com.fiap_produto_service.core.domain.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProdutoJpaGatewayTest {

    @InjectMocks
    private ProdutoJpaGateway produtoJpaGateway;

    @Mock
    private ProdutoRepository produtoRepository;

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

        ProdutoEntity produtoEntity = new ProdutoEntity(
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getCriadoEm(),
                produto.getAtualizadoEm()
        );
        ReflectionTestUtils.setField(produtoEntity, "id", 1L);

        when(produtoRepository.save(any())).thenReturn(produtoEntity);

        // Act
        Long id = produtoJpaGateway.criar(
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now());

        // Assert
        verify(produtoRepository, times(1)).save(any());
        assertEquals(1L, id);
    }

    @Test
    void testDeveRetornarProdutoAoEncontrarSku() {
        // Arrange
        String sku = "123";
        ProdutoEntity produtoEntity = new ProdutoEntity(
                sku,
                "Calça",
                "Calça jeans azul",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        ReflectionTestUtils.setField(produtoEntity, "id", 1L);

        when(produtoRepository.findBySku(sku)).thenReturn(Optional.of(produtoEntity));

        // Act
        Produto produto = produtoJpaGateway.recuperaPorSku(sku);

        // Assert
        assertNotNull(produto);
        assertEquals(produtoEntity.getId(), produto.getId());
        assertEquals(produtoEntity.getSku(), produto.getSku());
        assertEquals(produtoEntity.getNome(), produto.getNome());
        assertEquals(produtoEntity.getDescricao(), produto.getDescricao());
        assertEquals(produtoEntity.getPreco(), produto.getPreco());

        verify(produtoRepository, times(1)).findBySku(sku);
    }

    @Test
    void testDeveAtualizarProdutoComSucesso() {
        // Arrange
        ProdutoEntity produtoEntity = new ProdutoEntity(
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now()

        );

        when(produtoRepository.findBySku(produtoEntity.getSku())).thenReturn(Optional.of(produtoEntity));

        // Act
        Produto produto = produtoJpaGateway.atualiza(
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(200.00),
                LocalDateTime.now());

        // Assert
        assertNotNull(produto);
        assertEquals(BigDecimal.valueOf(200.00), produto.getPreco());

    }
}
