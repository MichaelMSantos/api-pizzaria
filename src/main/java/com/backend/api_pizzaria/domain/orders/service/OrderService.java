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

    public OrderResponseDTO sendOrder(UUID orderId){
        Order order =orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("O pedido não existe."));

        order.setDraft(false);
        orderRepository.save(order);

        return new OrderResponseDTO(
                order.getId(),
                order.getTableNumber(),
                order.getStatus(),
                order.getDraft(),
                order.getName());
    }

    public OrderResponseDTO finishOrder(UUID orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("O Pedido não existe."));

        order.setStatus(true);
        orderRepository.save(order);

        return new OrderResponseDTO(
                order.getId(),
                order.getTableNumber(),
                order.getStatus(),
                order.getDraft(),
                order.getName());
    }
}
