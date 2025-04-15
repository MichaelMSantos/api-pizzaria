package com.backend.api_pizzaria.domain.products.services;

import com.backend.api_pizzaria.domain.categories.Category;
import com.backend.api_pizzaria.domain.categories.ICategoryRepository;
import com.backend.api_pizzaria.domain.products.IProductRepository;
import com.backend.api_pizzaria.domain.products.Product;
import com.backend.api_pizzaria.domain.products.dtos.ProductResponseDTO;
import com.backend.api_pizzaria.domain.products.dtos.ProductRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    IProductRepository productRepository;
    @Autowired
    ICategoryRepository categoryRepository;

    public ProductResponseDTO newProduct(ProductRequestDTO dto, MultipartFile bannerFile) {
        Category category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        String uploadDir = "uploads";
        try {
            Files.createDirectories(Paths.get(uploadDir));

            String filename = UUID.randomUUID() + "_" + bannerFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, filename);
            Files.write(filePath, bannerFile.getBytes());

            dto = new ProductRequestDTO(dto.name(), dto.price(), dto.description(), filename, dto.categoryId());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar o arquivo", e);
        }

        Product product = new Product(
                dto.name(),
                dto.price(),
                dto.description(),
                dto.banner(),
                category
        );

        Product saved = productRepository.save(product);

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
