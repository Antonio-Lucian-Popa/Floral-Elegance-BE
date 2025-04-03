package com.asusoftware.FloralElegance.user.controller;

import com.asusoftware.FloralElegance.config.KeycloakService;
import com.asusoftware.FloralElegance.user.model.dto.UserDto;
import com.asusoftware.FloralElegance.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final KeycloakService keycloakService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestParam String email,
                                                     @RequestParam String password) {
        return ResponseEntity.ok(keycloakService.loginUser(email, password));
    }
}
