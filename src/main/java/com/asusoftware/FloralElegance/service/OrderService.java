package com.asusoftware.FloralElegance.service;

import com.asusoftware.FloralElegance.config.JwtUtil;
import com.asusoftware.FloralElegance.exception.NotFoundException;
import com.asusoftware.FloralElegance.model.Decoration;
import com.asusoftware.FloralElegance.model.Flower;
import com.asusoftware.FloralElegance.model.Order;
import com.asusoftware.FloralElegance.model.dto.OrderDto;
import com.asusoftware.FloralElegance.model.dto.OrderRequestDto;
import com.asusoftware.FloralElegance.model.dto.mapper.OrderMapper;
import com.asusoftware.FloralElegance.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final FlowerRepository flowerRepository;
    private final DecorationRepository decorationRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> getAllForUser(UUID userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orderMapper.toDtoList(orders);
    }

    public OrderDto getById(UUID orderId, UUID userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to access this order");
        }

        return orderMapper.toDto(order);
    }

    @Transactional
    public OrderDto create(UUID userId, OrderRequestDto dto) {
        Flower flower = flowerRepository.findById(dto.getFlowerId())
                .orElseThrow(() -> new RuntimeException("Flower not found"));

        Decoration decoration = null;
        if (dto.getDecorationId() != null) {
            decoration = decorationRepository.findById(dto.getDecorationId())
                    .orElseThrow(() -> new RuntimeException("Decoration not found"));
        }

        double total = flower.getPrice() + (decoration != null ? decoration.getPrice() : 0);

        LocalDateTime deliveryDate = LocalDateTime.parse(dto.getDeliveryDate());

        if (deliveryDate.isBefore(LocalDateTime.now())) {
            throw new NotFoundException("Delivery date must be in the future");
        }

        Order order = Order.builder()
                .flower(flower)
                .decoration(decoration)
                .decorationColor(dto.getDecorationColor())
                .message(dto.getMessage())
                .totalPrice(total)
                .deliveryAddress(dto.getDeliveryAddress())
                .deliveryDate(LocalDateTime.parse(dto.getDeliveryDate()))
                .status("pending")
                .userId(userId)
                .build();

        return orderMapper.toDto(orderRepository.save(order));
    }

    public boolean cancel(UUID orderId, UUID userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order not found"));

        if (!order.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to cancel this order");
        }

        if (!order.getStatus().equals("pending")) {
            throw new RuntimeException("Only pending orders can be canceled");
        }

        order.setStatus("cancelled");
        orderRepository.save(order);
        return true;
    }
}
