-- Users and Authentication
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    role ENUM('ADMIN', 'EDITOR', 'USER') NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE', 'BANNED') NOT NULL DEFAULT 'ACTIVE',
    last_login DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Sectors (domaines d'activité)
CREATE TABLE sectors (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Locations (villes/régions)
CREATE TABLE locations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    city VARCHAR(100) NOT NULL,
    region VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Main Startups table
CREATE TABLE startups (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    slug VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    short_description VARCHAR(500),
    sector_id BIGINT,
    location_id BIGINT,
    founding_date DATE,
    website_url VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    logo_url VARCHAR(255),
    employee_count INT,
    status ENUM('DRAFT', 'PENDING', 'PUBLISHED', 'ARCHIVED') DEFAULT 'DRAFT',
    verified BOOLEAN DEFAULT FALSE,
    created_by BIGINT,
    updated_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (sector_id) REFERENCES sectors(id),
    FOREIGN KEY (location_id) REFERENCES locations(id),
    FOREIGN KEY (created_by) REFERENCES users(id),
    FOREIGN KEY (updated_by) REFERENCES users(id)
);

-- Team Members
CREATE TABLE team_members (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    startup_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    role VARCHAR(100),
    bio TEXT,
    image_url VARCHAR(255),
    linkedin_url VARCHAR(255),
    email VARCHAR(255),
    order_index INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (startup_id) REFERENCES startups(id) ON DELETE CASCADE
);

-- Social Media Links
CREATE TABLE social_links (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    startup_id BIGINT NOT NULL,
    platform ENUM('LINKEDIN', 'TWITTER', 'FACEBOOK', 'INSTAGRAM', 'YOUTUBE') NOT NULL,
    url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (startup_id) REFERENCES startups(id) ON DELETE CASCADE
);

-- Job Postings
CREATE TABLE job_postings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    startup_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    type ENUM('FULL_TIME', 'PART_TIME', 'INTERNSHIP', 'FREELANCE') NOT NULL,
    location_type ENUM('REMOTE', 'ONSITE', 'HYBRID') NOT NULL,
    experience_level VARCHAR(100),
    salary_range VARCHAR(100),
    status ENUM('ACTIVE', 'CLOSED', 'DRAFT') DEFAULT 'DRAFT',
    published_at DATETIME,
    expires_at DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (startup_id) REFERENCES startups(id) ON DELETE CASCADE
);

-- Media Gallery
CREATE TABLE media (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    startup_id BIGINT NOT NULL,
    type ENUM('IMAGE', 'VIDEO', 'DOCUMENT') NOT NULL,
    url VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    description TEXT,
    order_index INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (startup_id) REFERENCES startups(id) ON DELETE CASCADE
);

-- Tags
CREATE TABLE tags (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Startup Tags (relation table)
CREATE TABLE startup_tags (
    startup_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (startup_id, tag_id),
    FOREIGN KEY (startup_id) REFERENCES startups(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id) ON DELETE CASCADE
);

-- Audit Logs
CREATE TABLE audit_logs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    action VARCHAR(100) NOT NULL,
    entity_type VARCHAR(50) NOT NULL,
    entity_id BIGINT NOT NULL,
    old_values JSON,
    new_values JSON,
    ip_address VARCHAR(45),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- User Sessions
CREATE TABLE sessions (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    ip_address VARCHAR(45),
    user_agent TEXT,
    expires_at DATETIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Settings
CREATE TABLE settings (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    key_name VARCHAR(100) NOT NULL UNIQUE,
    value TEXT,
    type ENUM('STRING', 'NUMBER', 'BOOLEAN', 'JSON') NOT NULL,
    description TEXT,
    updated_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (updated_by) REFERENCES users(id)
);

-- Create necessary indexes
CREATE INDEX idx_startups_status ON startups(status);
CREATE INDEX idx_startups_sector ON startups(sector_id);
CREATE INDEX idx_startups_location ON startups(location_id);
CREATE INDEX idx_job_postings_status ON job_postings(status);
CREATE INDEX idx_team_members_startup ON team_members(startup_id);
CREATE INDEX idx_social_links_startup ON social_links(startup_id);
CREATE INDEX idx_media_startup ON media(startup_id);
CREATE INDEX idx_audit_logs_user ON audit_logs(user_id);
CREATE INDEX idx_audit_logs_entity ON audit_logs(entity_type, entity_id);