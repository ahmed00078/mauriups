package com.mauriups.mauriups.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "startups")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true) // Or @Column if mandatory
    private String sector;

    @Column(nullable = true)
    private String location;

    @Column(nullable = true)
    private String website;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;
}
