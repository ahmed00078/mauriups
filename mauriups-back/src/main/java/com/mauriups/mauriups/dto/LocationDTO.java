package com.mauriups.mauriups.dto;

import lombok.Data;

@Data
public class LocationDTO {
    private String city;
    private String region;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
