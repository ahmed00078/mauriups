-- Startups
CREATE TABLE startups (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name VARCHAR(255) NOT NULL,
  description TEXT,
  sector VARCHAR(100),
  founding_date DATE,
  website VARCHAR(255),
  email VARCHAR(255),
  phone VARCHAR(20),
  location VARCHAR(255),
  logo_url TEXT,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Team Members
CREATE TABLE team_members (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  startup_id UUID REFERENCES startups(id),
  name VARCHAR(255),
  role VARCHAR(100),
  bio TEXT,
  image_url TEXT
);

-- Job Postings
CREATE TABLE job_postings (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  startup_id UUID REFERENCES startups(id),
  title VARCHAR(255),
  description TEXT,
  type VARCHAR(50),
  status VARCHAR(50)
);