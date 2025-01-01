package com.mauriups.mauriups.service;

import com.mauriups.mauriups.dto.StartupDTO;
import com.mauriups.mauriups.entity.Location;
import com.mauriups.mauriups.entity.Sector;
import com.mauriups.mauriups.entity.Startup;
import com.mauriups.mauriups.entity.StartupStatus;
import com.mauriups.mauriups.repository.LocationRepository;
import com.mauriups.mauriups.repository.SectorRepository;
import com.mauriups.mauriups.repository.StartupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StartupService {

    private static final Logger logger = LoggerFactory.getLogger(StartupService.class);

    @Autowired
    private StartupRepository startupRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private LocationRepository locationRepository;

    public List<Startup> getAllStartups() {
        return startupRepository.findAll();
    }

    public Startup getStartupById(Long id) {
        return startupRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Startup not found"));
    }

    /**
     * Crée une nouvelle startup à partir des données du DTO
     */
    @Transactional
    public Startup createStartup(StartupDTO dto) {
        // Vérification si le nom existe déjà
        if (startupRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Une startup avec ce nom existe déjà");
        }

        // Création de la nouvelle startup
        Startup startup = new Startup();

        // Copie des champs simples
        startup.setName(dto.getName());
        startup.setDescription(dto.getDescription());
        startup.setShortDescription(dto.getShortDescription());
        startup.setWebsiteUrl(dto.getWebsiteUrl());
        startup.setEmail(dto.getEmail());
        startup.setPhone(dto.getPhone());
        startup.setLogoUrl(dto.getLogoUrl());
        startup.setEmployeeCount(dto.getEmployeeCount());

        // Génération du slug à partir du nom
        startup.setSlug(generateSlug(dto.getName()));

        // Valeurs par défaut
        startup.setStatus(StartupStatus.DRAFT);
        startup.setVerified(false);

        // Gestion des relations
        if (dto.getSectorId() != null) {
            Sector sector = sectorRepository.findById(dto.getSectorId())
                    .orElseThrow(() -> new RuntimeException("Secteur non trouvé"));
            startup.setSector(sector);
        }

        if (dto.getLocationId() != null) {
            Location location = locationRepository.findById(dto.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location non trouvée"));
            startup.setLocation(location);
        }

        if (startup.getId() != null && startupRepository.existsById(startup.getId())) {
            throw new IllegalArgumentException("Une startup avec cet ID existe déjà");
        }
        if (startup.getSlug() == null) {
            startup.setSlug(generateSlug());
        }

        // Sauvegarde dans la base de données
        return startupRepository.save(startup);
    }

    /**
     * Génère un slug à partir du nom de la startup
     */
    private String generateSlug(String name) {
        return name.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "") // Supprime les caractères spéciaux
                .replaceAll("\\s+", "-")         // Remplace les espaces par des tirets
                .replaceAll("-+", "-")           // Évite les tirets multiples
                .replaceAll("^-", "")            // Supprime le tiret au début
                .replaceAll("-$", "");           // Supprime le tiret à la fin
    }

    private String generateSlug() {
        return "slug";
    }

    public Startup updateStartup(Long id, Startup startupDetails) {
        Startup startup = getStartupById(id);
        startup.setName(startupDetails.getName());
        startup.setDescription(startupDetails.getDescription());
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