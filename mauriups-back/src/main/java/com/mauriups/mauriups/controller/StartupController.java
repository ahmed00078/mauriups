package com.mauriups.mauriups.controller;

import com.mauriups.mauriups.dto.StartupDTO;
import com.mauriups.mauriups.dto.StartupUpdateDTO;
import com.mauriups.mauriups.entity.Startup;
import com.mauriups.mauriups.service.StartupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/startups")
public class StartupController {

    private static final Logger logger = LoggerFactory.getLogger(StartupService.class);

    @Autowired
    private StartupService startupService;

    @GetMapping
    public List<Startup> getAllStartups() {
        return startupService.getAllStartups();
    }

    @GetMapping("/{id}")
    public Startup getStartupById(@PathVariable Long id) {
        return startupService.getStartupById(id);
    }

    @PostMapping
    public ResponseEntity<?> createStartup(@RequestBody StartupDTO startupDTO) {
        try {
            Startup createdStartup = startupService.createStartup(startupDTO);
            return ResponseEntity.ok(createdStartup);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Startup> updateStartup(@PathVariable Long id, @RequestBody StartupUpdateDTO updateDTO) {
        return ResponseEntity.ok(startupService.updateStartup(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStartup(@PathVariable Long id) {
        startupService.deleteStartup(id);
        return ResponseEntity.ok().build();
    }
}