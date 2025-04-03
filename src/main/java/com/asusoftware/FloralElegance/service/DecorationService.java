package com.asusoftware.FloralElegance.service;

import com.asusoftware.FloralElegance.model.Decoration;
import com.asusoftware.FloralElegance.model.dto.DecorationDto;
import com.asusoftware.FloralElegance.model.dto.mapper.DecorationMapper;
import com.asusoftware.FloralElegance.repository.DecorationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DecorationService {

    private final DecorationRepository decorationRepository;
    private final DecorationMapper decorationMapper;

    public List<DecorationDto> getAll() {
        return decorationMapper.toDtoList(decorationRepository.findAll());
    }

    public DecorationDto getById(UUID id) {
        Decoration decoration = decorationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Decoration not found"));
        return decorationMapper.toDto(decoration);
    }

    // ADMIN DECORATION MANAGEMENT
    public DecorationDto create(DecorationDto dto) {
        Decoration decoration = decorationMapper.toEntity(dto);
        return decorationMapper.toDto(decorationRepository.save(decoration));
    }

    public DecorationDto update(UUID id, DecorationDto dto) {
        Decoration decoration = decorationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Decoration not found"));
        decoration.setName(dto.getName());
        decoration.setDescription(dto.getDescription());
        decoration.setImage(dto.getImage());
        decoration.setPrice(dto.getPrice());
        decoration.setColors(decorationMapper.toEntityColors(dto.getColors()));
        return decorationMapper.toDto(decorationRepository.save(decoration));
    }

    public void deleteDecoration(UUID id) {
        decorationRepository.deleteById(id);
    }
}
