package com.backend.api_pizzaria.domain.orders.service;

import com.backend.api_pizzaria.domain.orders.IOrderRepository;
import com.backend.api_pizzaria.domain.orders.Order;
import com.backend.api_pizzaria.domain.orders.dtos.OrderRequestDTO;
import com.backend.api_pizzaria.domain.orders.dtos.OrderResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    IOrderRepository orderRepository;

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        Order order = new Order();
        order.setTableNumber(request.table());
        order.setName(request.name());

        Order saved = orderRepository.save(order);

        return new OrderResponseDTO(
                saved.getId(),
                saved.getTableNumber(),
                saved.getDraft(),
                saved.getStatus(),
                saved.getName()
        );
    }

    public void deleteOrder(UUID orderId) {
        orderRepository.deleteById(orderId);
    }

    public List<OrderResponseDTO> findAll(){
        return orderRepository.findAll()
                .stream()
                .map(OrderResponseDTO::new)
                .toList();
    }
}
