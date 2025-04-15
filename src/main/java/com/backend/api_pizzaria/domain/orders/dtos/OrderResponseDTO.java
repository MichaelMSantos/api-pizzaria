package com.backend.api_pizzaria.domain.orders.dtos;

import com.backend.api_pizzaria.domain.orders.Order;

import java.util.UUID;

public record OrderResponseDTO(
        UUID id,
        Integer table,
        Boolean draft,
        Boolean status,
        String name
) {
    public OrderResponseDTO(Order order){
        this(order.getId(), order.getTableNumber(), order.getStatus(), order.getDraft(), order.getName());
    }
};
