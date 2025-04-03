package com.asusoftware.FloralElegance.controller;

import com.asusoftware.FloralElegance.model.dto.OrderDto;
import com.asusoftware.FloralElegance.model.dto.OrderRequestDto;
import com.asusoftware.FloralElegance.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDto>> getUserOrders(@PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getAllForUser(userId));
    }

    @GetMapping("/{orderId}/user/{userId}")
    public ResponseEntity<OrderDto> getById(@PathVariable UUID orderId, @PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.getById(orderId, userId));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<OrderDto> create(@PathVariable UUID userId, @RequestBody OrderRequestDto request) {
        return ResponseEntity.ok(orderService.create(userId, request));
    }

    @PutMapping("/{orderId}/cancel/user/{userId}")
    public ResponseEntity<?> cancel(@PathVariable UUID orderId, @PathVariable UUID userId) {
        return ResponseEntity.ok(orderService.cancel(orderId, userId));
    }
}
