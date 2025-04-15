package com.backend.api_pizzaria.domain.products;

import com.backend.api_pizzaria.domain.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory(Category category);
}
