package com.mauriups.mauriups.repository;

import com.mauriups.mauriups.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    boolean existsByCity(String city);
}
