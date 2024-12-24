// src/main/java/com/example/startupdirectory/service/StartupService.java
package com.mauriups.mauriups.service;

import com.mauriups.mauriups.entity.Startup;
import com.mauriups.mauriups.repository.StartupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StartupService {

    @Autowired
    private StartupRepository startupRepository;

    public List<Startup> getAllStartups() {
        return startupRepository.findAll();
    }

    public Startup getStartupById(Long id) {
        return startupRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Startup not found"));
    }

    public Startup createStartup(Startup startup) {
        return startupRepository.save(startup);
    }

    public Startup updateStartup(Long id, Startup startupDetails) {
        Startup startup = getStartupById(id);
        startup.setName(startupDetails.getName());
        startup.setDescription(startupDetails.getDescription());
        startup.setSector(startupDetails.getSector());
        startup.setLocation(startupDetails.getLocation());
        startup.setWebsite(startupDetails.getWebsite());
        startup.setEmail(startupDetails.getEmail());
        startup.setPhone(startupDetails.getPhone());
        return startupRepository.save(startup);
    }

    public void deleteStartup(Long id) {
        Startup startup = getStartupById(id);
        startupRepository.delete(startup);
    }
}