package com.asusoftware.FloralElegance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders") // "order" e cuvânt rezervat în SQL
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private Flower flower;

    @ManyToOne
    private Decoration decoration;

    private String decorationColor; // HEX color or name

    @Size(max = 150)
    private String message;

    @Positive
    private double totalPrice;

    private LocalDateTime createdAt;

    @NotBlank
    private UUID userId; // din JWT

    @NotBlank
    private String status; // pending, processing, delivered, cancelled

    @NotBlank
    private String deliveryAddress;

    private LocalDateTime deliveryDate;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}