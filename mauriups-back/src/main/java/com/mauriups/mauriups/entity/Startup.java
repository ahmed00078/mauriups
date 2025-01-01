package com.mauriups.mauriups.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "startups")
public class Startup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 500)
    private String shortDescription;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "founding_date")
    private LocalDate foundingDate;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "employee_count")
    private Integer employeeCount;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('DRAFT', 'PENDING', 'PUBLISHED', 'ARCHIVED')")
    private StartupStatus status = StartupStatus.DRAFT;

    @Column
    private Boolean verified = false;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getSector() {
        if (sector == null) {
            return null;
        }
        return sector.getId();
    }

    public Long getLocation() {
        if (location == null) {
            return null;
        }
        return location.getId();
    }

    public String getWebsite() {
        return websiteUrl;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setWebsite(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public void setStatus(StartupStatus status) {
        this.status = status;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }
}
