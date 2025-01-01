package com.mauriups.mauriups.service;

import com.mauriups.mauriups.dto.LocationDTO;
import com.mauriups.mauriups.entity.Location;
import com.mauriups.mauriups.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
    }

    public Location createLocation(LocationDTO dto) {
        Location location = new Location();
        location.setCity(dto.getCity());
        location.setRegion(dto.getRegion());
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, LocationDTO dto) {
        Location location = getLocationById(id);
        location.setCity(dto.getCity());
        location.setRegion(dto.getRegion());
        return locationRepository.save(location);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
