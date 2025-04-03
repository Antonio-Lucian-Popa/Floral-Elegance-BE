package com.asusoftware.FloralElegance.user.service;

import com.asusoftware.FloralElegance.config.KeycloakService;
import com.asusoftware.FloralElegance.user.model.User;
import com.asusoftware.FloralElegance.user.model.dto.UserDto;
import com.asusoftware.FloralElegance.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KeycloakService keycloakService;

    @Transactional
    public UserDto createUser(UserDto userDTO) {
        String keycloakId = keycloakService.createKeycloakUser(userDTO);
        User user = modelMapper.map(userDTO, User.class);
        user.setKeycloakId(UUID.fromString(keycloakId));
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto getUserByKeycloakId(UUID keycloakId) {
        User user = userRepository.findByKeycloakId(keycloakId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .toList();
    }
}