package com.backend.api_pizzaria.domain.products.dtos;

import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String price,
        String description,
        String banner,
        UUID categoryId
) {}
