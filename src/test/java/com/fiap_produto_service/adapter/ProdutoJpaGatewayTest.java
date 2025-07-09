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
import java.util.List;
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
        String sku = produtoJpaGateway.criar(
                "123",
                "Calça",
                "Calça jeans azul com cintura alta",
                BigDecimal.valueOf(199.90),
                LocalDateTime.now(),
                LocalDateTime.now());

        // Assert
        verify(produtoRepository, times(1)).save(any());
        assertEquals("123", sku);
    }

    @Test
    void testDeveRetornarProdutoAoEncontrarSkus() {
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

        when(produtoRepository.findBySkuIn(List.of(sku))).thenReturn(List.of(produtoEntity));

        // Act
        List<Produto> produto = produtoJpaGateway.recuperaPorSkus(List.of(sku));

        // Assert
        assertNotNull(produto);
        assertEquals(produtoEntity.getId(), produto.get(0).getId());
        assertEquals(produtoEntity.getSku(), produto.get(0).getSku());
        assertEquals(produtoEntity.getNome(), produto.get(0).getNome());
        assertEquals(produtoEntity.getDescricao(), produto.get(0).getDescricao());
        assertEquals(produtoEntity.getPreco(), produto.get(0).getPreco());

        verify(produtoRepository, times(1)).findBySkuIn(List.of(sku));
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

    @Test
    void testDeveDeletarProdutoAoEncontrarSku() {
        // Arrange
        String sku = "123";

        // Act
        produtoJpaGateway.deleta(sku);

        // Assert
        verify(produtoRepository, times(1)).deleteBySku(sku);
    }
}
