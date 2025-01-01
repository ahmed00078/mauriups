package com.mauriups.mauriups.repository;

import com.mauriups.mauriups.entity.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, Long> {
    // Spring Data JPA génère automatiquement les méthodes CRUD de base
    boolean existsByName(String name);
}