package com.etournament.proj.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etournament.proj.model.Match;
import com.etournament.proj.repo.MatchesRepo;


@Service
public class MatchesService {
	
	@Autowired
	MatchesRepo matchesRepo;

	public List<Match> getAllMatches(){
		List<Match> matches= new ArrayList<Match>();
		matchesRepo.findAll().forEach(match->matches.add(match));
		return matches;	
	}
	
	public Match getMatchesById(int id) {
		return matchesRepo.findById(id).get();
	}
	
	public void saveOrUpdate(Match match) {
		matchesRepo.save(match);	
	}
	
	
	public void delete(int id) {
        matchesRepo.deleteById(id);
    }
	
}
