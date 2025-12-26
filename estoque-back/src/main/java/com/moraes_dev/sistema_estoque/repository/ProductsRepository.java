package com.moraes_dev.sistema_estoque.repository;

import com.moraes_dev.sistema_estoque.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    public ProductsEntity findByBarCode(String s);
}
