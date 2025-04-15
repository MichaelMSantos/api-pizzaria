package com.backend.api_pizzaria.domain.products.dtos;

import java.util.UUID;

public record ItemRequestDTO(UUID orderId, UUID productId, Integer amount) {
}
