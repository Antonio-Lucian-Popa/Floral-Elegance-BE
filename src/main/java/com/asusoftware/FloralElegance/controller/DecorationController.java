package com.asusoftware.FloralElegance.controller;

import com.asusoftware.FloralElegance.model.dto.DecorationDto;
import com.asusoftware.FloralElegance.service.DecorationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/decorations")
@RequiredArgsConstructor
public class DecorationController {

    private final DecorationService decorationService;

    @GetMapping("/decorations")
    public ResponseEntity<List<DecorationDto>> getAllDecorations() {
        return ResponseEntity.ok(decorationService.getAll());
    }

    @GetMapping("/decorations/{id}")
    public ResponseEntity<DecorationDto> getDecorationById(@PathVariable UUID id) {
        return ResponseEntity.ok(decorationService.getById(id));
    }
}
