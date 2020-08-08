package com.etournament.proj.security.repository;

import com.etournament.proj.security.model.ERole;
import com.etournament.proj.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
