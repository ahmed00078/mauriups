package com.mauriups.mauriups.repository;

import com.mauriups.mauriups.entity.TeamMember;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByStartupIdOrderByOrderIndexAsc(Long startupId);
}