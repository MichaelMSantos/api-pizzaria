package com.backend.api_pizzaria.domain.products.services;

import com.backend.api_pizzaria.domain.categories.Category;
import com.backend.api_pizzaria.domain.categories.ICategoryRepository;
import com.backend.api_pizzaria.domain.products.IProductRepository;
import com.backend.api_pizzaria.domain.products.Product;
import com.backend.api_pizzaria.domain.products.dtos.ProductResponseDTO;
import com.backend.api_pizzaria.domain.products.dtos.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository categoryRepository;

    public ProductResponseDTO newProduct(ProductRequestDTO requestDTO) {
        Category category = categoryRepository.findById(requestDTO.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Product saveProduct = new Product(
                requestDTO.name(),
                requestDTO.price(),
                requestDTO.description(),
                requestDTO.banner(),
                category
        );

        Product saved = productRepository.save(saveProduct);
        return new ProductResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getPrice(),
                saved.getDescription(),
                saved.getBanner(),
                saved.getCategory().getId()
        );
    }

    public List<ProductResponseDTO> getProductsByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        List<Product> products = productRepository.findByCategory(category);

        return products.stream().map(p -> new ProductResponseDTO(
                p.getId(),
                p.getName(),
                p.getPrice(),
                p.getDescription(),
                p.getBanner(),
                p.getCategory().getId()
        )).toList();
    }

}
