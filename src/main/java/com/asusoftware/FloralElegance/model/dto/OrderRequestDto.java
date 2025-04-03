package com.asusoftware.FloralElegance.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDto {
    private UUID flowerId;
    private UUID decorationId; // optional
    private String decorationColor; // optional
    private String message; // optional
    private String deliveryAddress;
    private String deliveryDate; // ISO format
}