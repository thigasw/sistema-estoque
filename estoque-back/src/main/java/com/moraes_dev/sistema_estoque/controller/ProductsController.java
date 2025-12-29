package com.moraes_dev.sistema_estoque.controller;

import com.moraes_dev.sistema_estoque.DTO.CreateProdutcDTO;
import com.moraes_dev.sistema_estoque.entity.ProductsEntity;
import com.moraes_dev.sistema_estoque.service.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/estoque")
@AllArgsConstructor
public class ProductsController {

    ProductsService productsService;

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
        return productsService.getAllProducts();
    }

    @PostMapping("/produtos/csv")
    public ResponseEntity<?> createProductsByCsv(@RequestParam("file") MultipartFile file){
        return productsService.createProductsByCsv(file);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> createProduct(@RequestBody CreateProdutcDTO produto){
        return productsService.createProduct(produto);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductsEntity product){
        return productsService.updateProduct(id, product);
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        return productsService.deleteProduct(id);
    }

}
