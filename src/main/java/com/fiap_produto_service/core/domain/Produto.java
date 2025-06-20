package com.fiap_produto_service.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Produto {

    private Long id;

    private String sku;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private LocalDateTime criadoEm;

    private LocalDateTime atualizadoEm;

    public Produto(String nome, String descricao, BigDecimal preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        LocalDateTime agora = LocalDateTime.now();
        this.criadoEm = agora;
        this.atualizadoEm = agora;
        this.sku = UUID.randomUUID().toString();
    }

    public Produto(Long id, String sku, String nome, String descricao, BigDecimal preco, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Produto(String sku, String nome, String descricao, BigDecimal preco) {
        this.sku = sku;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }
}
