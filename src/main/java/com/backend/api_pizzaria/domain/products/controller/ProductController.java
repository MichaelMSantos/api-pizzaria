package com.backend.api_pizzaria.domain.products.controller;

import com.backend.api_pizzaria.domain.products.dtos.ProductRequestDTO;
import com.backend.api_pizzaria.domain.products.dtos.ProductResponseDTO;
import com.backend.api_pizzaria.domain.products.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("description") String description,
            @RequestParam("categoryId") UUID categoryId,
            @RequestParam("banner") MultipartFile bannerFile
    ) {
        ProductRequestDTO dto = new ProductRequestDTO(name, price, description, bannerFile.getOriginalFilename(), categoryId);
        ProductResponseDTO response = productService.newProduct(dto, bannerFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByCategory(@PathVariable UUID categoryId){
        List<ProductResponseDTO> products = productService.getProductsByCategory(categoryId);

        return ResponseEntity.ok(products);
    }
}
