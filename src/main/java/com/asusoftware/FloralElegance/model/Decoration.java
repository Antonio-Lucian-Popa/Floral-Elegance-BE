package com.asusoftware.FloralElegance.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Decoration {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    private String name;

    @Size(max = 500)
    private String description;

    @Positive
    private double price;

    @NotBlank
    private String image; // URL

    @ElementCollection
    @CollectionTable(name = "decoration_colors", joinColumns = @JoinColumn(name = "decoration_id"))
    private List<ColorOption> colors;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ColorOption {
        private String name;
        private String code; // HEX
    }
}
