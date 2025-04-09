package com.asusoftware.FloralElegance.service;

import com.asusoftware.FloralElegance.exception.FileStorageException;
import com.asusoftware.FloralElegance.model.Flower;
import com.asusoftware.FloralElegance.model.dto.FlowerDto;
import com.asusoftware.FloralElegance.model.dto.mapper.FlowerMapper;
import com.asusoftware.FloralElegance.repository.FlowerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlowerService {

    private final FlowerRepository flowerRepository;
    private final FlowerMapper flowerMapper;

    public List<FlowerDto> getAllFlowers() {
        return flowerMapper.toDtoList(flowerRepository.findAll());
    }

    public FlowerDto getById(UUID id) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flower not found"));
        return flowerMapper.toDto(flower);
    }

    public List<FlowerDto> getByTag(String tag) {
        return flowerMapper.toDtoList(flowerRepository.findByTag(tag));
    }

    public List<FlowerDto> getPopularFlowers() {
        // Vom implementa aici în pasul 7 logica reală pentru "popular"
        // Temporar returnăm florile cu tag = "Popular"
        return flowerMapper.toDtoList(flowerRepository.findByTag("Popular"));
    }

    public List<FlowerDto> filterByPrice(Double minPrice, Double maxPrice, String sortBy, String direction) {
        List<Flower> flowers = flowerRepository.findAll();

        return flowers.stream()
                .filter(f -> minPrice == null || f.getPrice() >= minPrice)
                .filter(f -> maxPrice == null || f.getPrice() <= maxPrice)
                .sorted((a, b) -> {
                    if ("desc".equalsIgnoreCase(direction)) {
                        return Double.compare(b.getPrice(), a.getPrice());
                    } else {
                        return Double.compare(a.getPrice(), b.getPrice());
                    }
                })
                .map(flowerMapper::toDto)
                .toList();
    }

    public List<Flower> findPopularFlowersLast30Days() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        return flowerRepository.findPopularFlowersFromDate(thirtyDaysAgo);
    }



    // ADMIN FLOWER MANAGEMENT
    public FlowerDto create(FlowerDto dto) {
        Flower flower = flowerMapper.toEntity(dto);
        return flowerMapper.toDto(flowerRepository.save(flower));
    }

    public FlowerDto update(UUID id, FlowerDto dto) {
        Flower flower = flowerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flower not found"));
        flower.setName(dto.getName());
        flower.setDescription(dto.getDescription());
        flower.setImage(dto.getImage());
        flower.setPrice(dto.getPrice());
        flower.setTag(dto.getTag());
        return flowerMapper.toDto(flowerRepository.save(flower));
    }

    public String uploadImage(UUID flowerId, MultipartFile file) {
        try {
            Flower flower = flowerRepository.findById(flowerId)
                    .orElseThrow(() -> new RuntimeException("Flower not found"));

            String folderPath = "src/main/resources/static/images/flowers/";
            Files.createDirectories(Paths.get(folderPath));

            String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path fullPath = Paths.get(folderPath + filename);

            Files.write(fullPath, file.getBytes());
            String imageUrl = "/images/flowers/" + filename;

            flower.setImage(imageUrl);
            flowerRepository.save(flower);

            return imageUrl;
        } catch (IOException e) {
            throw new FileStorageException("Failed to save image: " + e.getMessage());
        }

    }

    public void delete(UUID id) {
        flowerRepository.deleteById(id);
    }
}

