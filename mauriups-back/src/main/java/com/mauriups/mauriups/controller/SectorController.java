package com.mauriups.mauriups.controller;

import com.mauriups.mauriups.dto.SectorDTO;
import com.mauriups.mauriups.entity.Sector;
import com.mauriups.mauriups.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sectors")
public class SectorController {
    @Autowired
    private SectorService sectorService;

    @GetMapping
    public List<Sector> getAllSectors() {
        return sectorService.getAllSectors();
    }

    @GetMapping("/{id}")
    public Sector getSectorById(@PathVariable Long id) {
        return sectorService.getSectorById(id);
    }

    @PostMapping
    public ResponseEntity<Sector> createSector(@RequestBody SectorDTO sectorDTO) {
        try {
            Sector sector = sectorService.createSector(sectorDTO);
            return ResponseEntity.ok(sector);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sector> updateSector(@PathVariable Long id, @RequestBody SectorDTO sectorDTO) {
        try {
            Sector sector = sectorService.updateSector(id, sectorDTO);
            return ResponseEntity.ok(sector);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSector(@PathVariable Long id) {
        try {
            sectorService.deleteSector(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}