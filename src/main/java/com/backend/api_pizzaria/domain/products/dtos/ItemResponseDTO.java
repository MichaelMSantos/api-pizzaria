package com.backend.api_pizzaria.domain.products.dtos;

import com.backend.api_pizzaria.domain.products.items.Item;

import java.util.UUID;

public record ItemResponseDTO(
        UUID id,
        Integer amount,
        UUID orderId,
        UUID productId
) {
    public ItemResponseDTO(Item item) {
        this(
                item.getId(),
                item.getAmount(),
                item.getOrder().getId(),
                item.getProduct().getId()
        );
    }
}
