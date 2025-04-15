package com.backend.api_pizzaria.domain.products.services;

import com.backend.api_pizzaria.domain.orders.IOrderRepository;
import com.backend.api_pizzaria.domain.orders.Order;
import com.backend.api_pizzaria.domain.products.IProductRepository;
import com.backend.api_pizzaria.domain.products.Product;
import com.backend.api_pizzaria.domain.products.dtos.ItemResponseDTO;
import com.backend.api_pizzaria.domain.products.items.IItemRepository;
import com.backend.api_pizzaria.domain.products.items.Item;
import com.backend.api_pizzaria.domain.products.dtos.ItemRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    IItemRepository itemRepository;
    @Autowired
    IOrderRepository orderRepository;
    @Autowired
    IProductRepository productRepository;

    public ItemResponseDTO createItem(ItemRequestDTO request) {
        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Item item = new Item();
        item.setOrder(order);
        item.setProduct(product);
        item.setAmount(request.amount());

        Item saved = itemRepository.save(item);
        return new ItemResponseDTO(saved);
    }

    public void deleteItem(UUID itemId) {
        itemRepository.deleteById(itemId);
    }
}
