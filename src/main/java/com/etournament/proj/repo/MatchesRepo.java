package com.etournament.proj.repo;


import org.springframework.data.repository.CrudRepository;

import com.etournament.proj.model.Match;



public interface MatchesRepo extends CrudRepository<Match, Integer> {
	
	
}
