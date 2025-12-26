package com.moraes_dev.sistema_estoque.DTO;


public record ProductsCsvDTO (String name,
                              String brand,
                              String category,
                              String subcategory,
                              String colorTone,
                              String amount,
                              String price,
                              String utility,
                              String skinType,
                              String finish,
                              String relatedProducts,
                              String imageUrl,
                              String barCode){
}
