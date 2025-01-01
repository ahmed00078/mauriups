package com.mauriups.mauriups.controller;

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
    public Startup createStartup(@RequestBody Startup startup) {
        startup.setId(null);
        return startupService.createStartup(startup);
    }

    @PutMapping("/{id}")
    public Startup updateStartup(@PathVariable Long id, @RequestBody Startup startup) {
        return startupService.updateStartup(id, startup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStartup(@PathVariable Long id) {
        startupService.deleteStartup(id);
        return ResponseEntity.ok().build();
    }
}