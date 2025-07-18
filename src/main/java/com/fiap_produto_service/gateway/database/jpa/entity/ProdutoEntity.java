package com.fiap_produto_service.gateway.database.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sku;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    public ProdutoEntity(String sku, String nome, String descricao, BigDecimal preco, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }
}
