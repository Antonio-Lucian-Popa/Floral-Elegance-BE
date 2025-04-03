package com.asusoftware.FloralElegance.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private UUID id;
    private FlowerDto flower;
    private DecorationDto decoration;
    private String decorationColor;
    private String message;
    private double totalPrice;
    private String status;
    private String deliveryAddress;
    private String deliveryDate;
    private String createdAt;
}
