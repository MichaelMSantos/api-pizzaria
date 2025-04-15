package com.backend.api_pizzaria.domain.products.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductRequestDTO(
        String name,
        String price,
        String description,
        String banner,
        UUID categoryId
) {}
