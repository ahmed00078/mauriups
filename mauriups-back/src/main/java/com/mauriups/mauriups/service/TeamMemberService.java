package com.mauriups.mauriups.service;

import com.mauriups.mauriups.dto.TeamMemberDTO;
import com.mauriups.mauriups.entity.Startup;
import com.mauriups.mauriups.entity.TeamMember;
import com.mauriups.mauriups.repository.StartupRepository;
import com.mauriups.mauriups.repository.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamMemberService {
    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private StartupRepository startupRepository;

    public List<TeamMember> getTeamMembersByStartup(Long startupId) {
        return teamMemberRepository.findByStartupIdOrderByOrderIndexAsc(startupId);
    }

    public TeamMember createTeamMember(TeamMemberDTO dto) {
        Startup startup = startupRepository.findById(dto.getStartupId())
                .orElseThrow(() -> new RuntimeException("Startup not found"));

        TeamMember member = new TeamMember();
        member.setStartup(startup);
        member.setName(dto.getName());
        member.setRole(dto.getRole());
        member.setBio(dto.getBio());
        member.setImageUrl(dto.getImageUrl());
        member.setLinkedinUrl(dto.getLinkedinUrl());
        member.setEmail(dto.getEmail());
        member.setOrderIndex(dto.getOrderIndex());

        return teamMemberRepository.save(member);
    }

    public TeamMember updateTeamMember(Long id, TeamMemberDTO dto) {
        TeamMember member = teamMemberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team member not found"));

        member.setName(dto.getName());
        member.setRole(dto.getRole());
        member.setBio(dto.getBio());
        member.setImageUrl(dto.getImageUrl());
        member.setLinkedinUrl(dto.getLinkedinUrl());
        member.setEmail(dto.getEmail());
        member.setOrderIndex(dto.getOrderIndex());

        return teamMemberRepository.save(member);
    }

    public void deleteTeamMember(Long id) {
        teamMemberRepository.deleteById(id);
    }
}
