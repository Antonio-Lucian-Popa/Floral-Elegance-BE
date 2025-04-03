package com.asusoftware.FloralElegance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flower {

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

    private String tag; // optional: "Popular", "New", etc.
}
