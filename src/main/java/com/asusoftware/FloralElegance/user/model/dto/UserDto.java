package com.asusoftware.FloralElegance.user.model.dto;

import com.asusoftware.FloralElegance.user.model.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserType userType;
}