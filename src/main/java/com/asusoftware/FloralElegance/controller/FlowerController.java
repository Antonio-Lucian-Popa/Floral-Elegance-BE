package com.asusoftware.FloralElegance.controller;

import com.asusoftware.FloralElegance.model.dto.FlowerDto;
import com.asusoftware.FloralElegance.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/flowers")
@RequiredArgsConstructor
public class FlowerController {

    private final FlowerService flowerService;

    @GetMapping("/all-flowers")
    public ResponseEntity<List<FlowerDto>> getAllFlowers() {
        return ResponseEntity.ok(flowerService.getAllFlowers());
    }

    @GetMapping("/flowers/{id}")
    public ResponseEntity<FlowerDto> getFlowerById(@PathVariable UUID id) {
        return ResponseEntity.ok(flowerService.getById(id));
    }

    @GetMapping("/flowers/tag/{tag}")
    public ResponseEntity<List<FlowerDto>> getFlowersByTag(@PathVariable String tag) {
        return ResponseEntity.ok(flowerService.getByTag(tag));
    }

    @GetMapping("/api/flowers/popular")
    public ResponseEntity<List<FlowerDto>> getPopularFlowers() {
        return ResponseEntity.ok(flowerService.getPopularFlowers());
    }

    @GetMapping("/filtered-flowers")
    public ResponseEntity<List<FlowerDto>> filterFlowers(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "price") String sort,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ResponseEntity.ok(flowerService.filterByPrice(minPrice, maxPrice, sort, direction));
    }
}
