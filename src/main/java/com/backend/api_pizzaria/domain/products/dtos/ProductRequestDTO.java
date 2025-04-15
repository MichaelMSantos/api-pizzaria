package com.backend.api_pizzaria.domain.products.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProductRequestDTO(
        @NotNull String name,
        @NotNull String price,
        @NotNull String description,
        @NotNull String banner,
        @NotNull UUID categoryId
) {
}
