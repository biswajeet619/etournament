package com.etournament.proj.repo;


import com.etournament.proj.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MatchesRepo extends JpaRepository<Match, Long> {


}
