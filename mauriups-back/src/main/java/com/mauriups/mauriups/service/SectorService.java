package com.mauriups.mauriups.service;

import com.mauriups.mauriups.dto.SectorDTO;
import com.mauriups.mauriups.entity.Sector;
import com.mauriups.mauriups.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SectorService {
    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public Sector getSectorById(Long id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found"));
    }

    public Sector createSector(SectorDTO sectorDTO) {
        if (sectorRepository.existsByName(sectorDTO.getName())) {
            throw new RuntimeException("Sector name already exists");
        }

        Sector sector = new Sector();
        sector.setName(sectorDTO.getName());
        sector.setDescription(sectorDTO.getDescription());

        return sectorRepository.save(sector);
    }

    public Sector updateSector(Long id, SectorDTO sectorDTO) {
        Sector sector = getSectorById(id);

        if (!sector.getName().equals(sectorDTO.getName()) &&
                sectorRepository.existsByName(sectorDTO.getName())) {
            throw new RuntimeException("Sector name already exists");
        }

        sector.setName(sectorDTO.getName());
        sector.setDescription(sectorDTO.getDescription());

        return sectorRepository.save(sector);
    }

    public void deleteSector(Long id) {
        Sector sector = getSectorById(id);
        sectorRepository.delete(sector);
    }
}