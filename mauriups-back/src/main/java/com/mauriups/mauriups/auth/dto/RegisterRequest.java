package com.mauriups.mauriups.auth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password_hash;
    private String first_name;
    private String last_name;

    public String getEmail() {
        return email;
    }

    public CharSequence getPassword() {
        return password_hash;
    }

    public Object getFirstName() {
        return first_name;
    }

    public Object getLastName() {
        return last_name;
    }
}