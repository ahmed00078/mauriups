package com.mauriups.mauriups.controller;

import com.mauriups.mauriups.dto.TeamMemberDTO;
import com.mauriups.mauriups.entity.TeamMember;
import com.mauriups.mauriups.service.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team-members")
public class TeamMemberController {
    @Autowired
    private TeamMemberService teamMemberService;

    @GetMapping("/startup/{startupId}")
    public List<TeamMember> getTeamMembersByStartup(@PathVariable Long startupId) {
        return teamMemberService.getTeamMembersByStartup(startupId);
    }

    @PostMapping
    public ResponseEntity<TeamMember> createTeamMember(@RequestBody TeamMemberDTO dto) {
        return ResponseEntity.ok(teamMemberService.createTeamMember(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeamMember> updateTeamMember(@PathVariable Long id, @RequestBody TeamMemberDTO dto) {
        return ResponseEntity.ok(teamMemberService.updateTeamMember(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeamMember(@PathVariable Long id) {
        teamMemberService.deleteTeamMember(id);
        return ResponseEntity.ok().build();
    }
}