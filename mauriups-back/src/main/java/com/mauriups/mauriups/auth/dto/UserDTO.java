package com.mauriups.mauriups.auth.dto;

import com.mauriups.mauriups.entity.User;
import com.mauriups.mauriups.entity.UserRole;
import com.mauriups.mauriups.entity.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String first_name;
    private String last_name;
    private String role;
    private String status;

    public UserDTO(Long id, String email, String firstName, String lastName, String role, String status) {
        this.id = id;
        this.email = email;
        this.first_name = firstName;
        this.last_name = lastName;
        this.role = role;
        this.status = status;
    }

    public static UserDTOBuilder builder() {
        return new UserDTOBuilder();
    }

    public static class UserDTOBuilder {
        private Long id;
        private String email;
        private String first_name;
        private String last_name;
        private String role;
        private String status;

        public UserDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDTOBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDTOBuilder first_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public UserDTOBuilder last_name(String last_name) {
            this.last_name = last_name;
            return this;
        }

        public UserDTOBuilder role(UserRole role) {
            this.role = role.name();
            return this;
        }

        public UserDTOBuilder status(UserStatus status) {
            this.status = status.name();
            return this;
        }

        public UserDTO build() {
            return new UserDTO(id, email, first_name, last_name, role, status);
        }
    }

    // Méthode utilitaire pour convertir User en UserDTO
    public static UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}