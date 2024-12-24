package com.mauriups.mauriups.auth.dto;

import com.mauriups.mauriups.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private String role;
    private String status;

    // Méthode utilitaire pour convertir User en UserDTO
    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .role(user.getRole().name())
                .status(user.getStatus().name())
                .build();
    }
}