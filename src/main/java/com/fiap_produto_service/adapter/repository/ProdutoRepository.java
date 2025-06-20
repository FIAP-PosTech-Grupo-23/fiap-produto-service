package com.fiap_produto_service.adapter.repository;

import com.fiap_produto_service.adapter.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {
    Optional<ProdutoEntity> findBySku(String sku);

    void deleteBySku(String sku);
}
