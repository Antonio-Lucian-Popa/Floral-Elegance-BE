package com.asusoftware.FloralElegance.model.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecorationDto {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private String image;
    private List<ColorDto> colors;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorDto {
        private String name;
        private String code;
    }
}
