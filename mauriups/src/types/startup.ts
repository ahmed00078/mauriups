// src/types/startup.ts
export interface Startup {
    id: string;
    name: string;
    description: string;
    sector: string;
    location: string;
    logo?: string;
    website?: string;
    team: TeamMember[];
}

export interface TeamMember {
    id: string;
    name: string;
    role: string;
    image?: string;
}