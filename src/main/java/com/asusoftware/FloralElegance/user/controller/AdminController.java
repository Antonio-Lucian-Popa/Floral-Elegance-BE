package com.asusoftware.FloralElegance.user.controller;

import com.asusoftware.FloralElegance.model.dto.DecorationDto;
import com.asusoftware.FloralElegance.model.dto.FlowerDto;
import com.asusoftware.FloralElegance.service.DecorationService;
import com.asusoftware.FloralElegance.service.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FlowerService flowerService;
    private final DecorationService decorationService;

    // Flower Admin
    @PostMapping("/flowers")
    public ResponseEntity<FlowerDto> createFlower(@RequestBody FlowerDto flowerDto) {
        return ResponseEntity.ok(flowerService.create(flowerDto));
    }

    @PostMapping("/flowers/{id}/upload")
    public ResponseEntity<String> uploadFlowerImage(@PathVariable UUID id,
                                                    @RequestParam("file") MultipartFile file) {
        String imageUrl = flowerService.uploadImage(id, file);
        return ResponseEntity.ok(imageUrl);
    }


    @PutMapping("/flowers/{id}")
    public ResponseEntity<FlowerDto> updateFlower(@PathVariable UUID id, @RequestBody FlowerDto flowerDto) {
        return ResponseEntity.ok(flowerService.update(id, flowerDto));
    }

    @DeleteMapping("/flowers/{id}")
    public ResponseEntity<Void> deleteFlower(@PathVariable UUID id) {
        flowerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Decoration Admin
    @PostMapping("/decorations")
    public ResponseEntity<DecorationDto> createDecoration(@RequestBody DecorationDto dto) {
        return ResponseEntity.ok(decorationService.create(dto));
    }

    @PutMapping("/decorations/{id}")
    public ResponseEntity<DecorationDto> updateDecoration(@PathVariable UUID id, @RequestBody DecorationDto dto) {
        return ResponseEntity.ok(decorationService.update(id, dto));
    }

    @DeleteMapping("/decorations/{id}")
    public ResponseEntity<Void> deleteDecoration(@PathVariable UUID id) {
        decorationService.deleteDecoration(id);
        return ResponseEntity.noContent().build();
    }
}