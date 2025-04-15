package com.backend.api_pizzaria.domain.products.items;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IItemRepository extends JpaRepository<Item, UUID> {
}
