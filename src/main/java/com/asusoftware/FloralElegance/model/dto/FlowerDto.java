package com.asusoftware.FloralElegance.model.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDto {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private String image;
    private String tag;
}