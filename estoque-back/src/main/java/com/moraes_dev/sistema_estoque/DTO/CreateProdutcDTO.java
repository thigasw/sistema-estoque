package com.moraes_dev.sistema_estoque.DTO;

import java.math.BigDecimal;

public record CreateProdutcDTO(String name,
                               String brand,
                               String category,
                               String subcategory,
                               String colorTone,
                               int amount,
                               BigDecimal price,
                               String utility,
                               String skinType,
                               String finish,
                               String relatedProducts,
                               String imageUrl,
                               String codeBar) {
}
