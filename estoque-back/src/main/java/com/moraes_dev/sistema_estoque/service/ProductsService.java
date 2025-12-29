package com.moraes_dev.sistema_estoque.service;

import com.moraes_dev.sistema_estoque.DTO.CreateProdutcDTO;
import com.moraes_dev.sistema_estoque.DTO.ProductsCsvDTO;
import com.moraes_dev.sistema_estoque.entity.ProductsEntity;
import com.moraes_dev.sistema_estoque.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductsService {

    ProductsRepository productsRepository;

    public ResponseEntity<?> getAllProducts(){
        try {
            log.info("Retornando todos os produtos cadastrados na base de dados.");
            return ResponseEntity.ok(productsRepository.findAll());
        } catch (Exception e) {
            log.error("Erro interno ao retornar os produtos cadastrados na plataforma.");
            return ResponseEntity.internalServerError().body("Erro interno ao retornar os produtos cadastrados na plataforma.");
        }
    }

    @Transactional
    public ResponseEntity<?> createProductsByCsv(MultipartFile productsCsv){
        log.info("Realizando o cadastro dos produtos enviados via CSV.");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(productsCsv.getInputStream()));
            List<String> lines = reader.lines().toList();
            reader.close();

            for (String line : lines){
                List<String> valores = List.of(line.split(","));

                if (productsRepository.findByBarCode(String.valueOf(valores.get(12))) != null || line.equals(lines.getFirst())) {
                    continue;
                }

                ProductsCsvDTO currentProduct = new ProductsCsvDTO(valores.get(0),
                        valores.get(1), valores.get(2), valores.get(3),
                        valores.get(4), valores.get(5), valores.get(6),
                        valores.get(7), valores.get(8), valores.get(9),
                        valores.get(10), valores.get(11), valores.get(12));
                ProductsEntity produtoAtual = new ProductsEntity(currentProduct);
                productsRepository.save(produtoAtual);
                log.info("Produto {} cadastrado com sucesso na base de dados.", produtoAtual.getName());
            }

            log.info("Produtos recebidos via CSV cadastrados com sucesso.");
            return ResponseEntity.ok("Produtos recebidos via CSV cadastrados com sucesso.");
        } catch (Exception e) {
            log.error("Erro interno no servidor durante o cadastro dos produtos enviados via CSV.", e);
            return ResponseEntity.internalServerError().body("Erro interno no servidor durante o cadastro dos produtos enviados via CSV.");
        }
    }

    @Transactional
    public ResponseEntity<?> createProduct(CreateProdutcDTO product){
        log.info("Realizando o cadastro do product {}.", product.name());

        try {
            if (productsRepository.findByBarCode(product.codeBar()) != null){
                log.info("Produto já cadastrado na base de dados com o código de barras {}", product.codeBar());
                return ResponseEntity.badRequest().body("Produto já cadastrado para o código de barras " + product.codeBar());
            } else {
                ProductsEntity productToSave = new ProductsEntity(product);
                productsRepository.save(productToSave);

                log.info("Produto {} cadastrado com sucesso no banco de dados.", product.name());
                return ResponseEntity.ok(product);
            }
        } catch (Exception e){
            log.error("Erro ao realizar o cadastro do product {} na base de dados.", product.name(),e);
            return ResponseEntity.internalServerError().body("Erro interno ao realizar o cadastro do product" + product.name() + "na base de dados.");
        }
    }

    @Transactional
    public ResponseEntity<?> updateProduct(long id, ProductsEntity product){
        log.info("Atualizando os dados do produto: {}.", product.getName());
        try {
            if (productsRepository.findById(product.getId()).isPresent()){
                productsRepository.save(product);

                log.info("Produto {} atualizado com sucesso na base de dados.", product.getName());
                return ResponseEntity.ok(product);
            } else {
                log.error("Nenhum produto com o id \"{}\" encontrado na base de dados.", product.getId());
                return ResponseEntity.badRequest().body("Nenhum produto com o id \"" + product.getId() + "\" encontrado na base de dados");
            }
        } catch (RuntimeException e) {
            log.error("Erro interno ao realizar a atualização das informações do produto \"{}\" na base de dados.", product.getName());
            return ResponseEntity.internalServerError().body("Erro interno ao realizar a atualização das informações do produto " + product.getName() + " na base de dados.");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteProduct(long id){
        log.info("Apagando o produto {}", id);
        try {
            if (productsRepository.findById(id).isPresent()) {
                productsRepository.deleteById(id);
                log.info("Produto {} apagado com sucesso da base de dados.", id);
                return ResponseEntity.ok("Produto " + id + " apagado com sucesso da base de dados.");
            } else {
                log.info("Nenhum produto cadastrado com o ID informado.");
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            log.error("Erro interno ao tentar apagar o produto {}", id, e);
            return ResponseEntity.internalServerError().body("Erro interno ao tentar apagar o produto " + id);
        }
    }
}
