package com.etournament.proj;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.etournament.proj.model.Match;


@RepositoryRestResource(collectionResourceRel = "matches",path="matches")
public interface MatchesRepo extends JpaRepository<Match, Integer> {

}
