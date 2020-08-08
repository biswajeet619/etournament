package com.etournament.proj.repo;


import com.etournament.proj.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface MatchesRepo extends JpaRepository<Match, UUID> {


}

