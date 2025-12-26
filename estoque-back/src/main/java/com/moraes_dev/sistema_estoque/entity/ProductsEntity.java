package com.moraes_dev.sistema_estoque.entity;

import com.moraes_dev.sistema_estoque.DTO.CreateProdutcDTO;
import com.moraes_dev.sistema_estoque.DTO.ProductsCsvDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "Marca")
    String brand;

    @Column(name = "Categoria")
    String category;

    @Column(name = "Subcategoria")
    String subCategory;

    @Column(name = "Cor/Tom")
    String colorTone;

    @Column(name = "Qtd")
    int amount;

    @Column(name = "Preço")
    BigDecimal price;

    @Column(name = "Pra que serve")
    String utility;

    @Column(name = "Tipo de Pele")
    String skinType;

    @Column(name = "Acabamento")
    String finish;

    @Column(name = "Produtos Relacionados")
    String relatedProducts;

    @Column(name = "Imagem URL")
    String imageUrl;

    @Column(name = "Código de Barras")
     String barCode;

    public ProductsEntity(ProductsCsvDTO produto){
        this.name = produto.name();
        this.brand = produto.brand();
        this.category = produto.category();
        this.subCategory = produto.subcategory();
        this.colorTone = produto.colorTone();
        this.amount = Integer.parseInt(produto.amount());
        this.price = new BigDecimal(produto.price());
        this.utility = produto.utility();
        this.skinType = produto.skinType();
        this.finish = produto.finish();
        this.relatedProducts = produto.relatedProducts();
        this.imageUrl = produto.imageUrl();
        this.barCode = produto.barCode();
    }

    public ProductsEntity(CreateProdutcDTO produto){
        this.name = produto.name();
        this.brand = produto.brand();
        this.category = produto.category();
        this.subCategory = produto.subcategory();
        this.colorTone = produto.colorTone();
        this.amount = produto.amount();
        this.price = produto.price();
        this.utility = produto.utility();
        this.skinType = produto.skinType();
        this.finish = produto.finish();
        this.relatedProducts = produto.relatedProducts();
        this.imageUrl = produto.imageUrl();
        this.barCode = produto.codeBar();
    }
}
