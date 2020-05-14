package com.etournament.proj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.etournament.proj.model.Match;
import com.etournament.proj.services.MatchesService;

@RestController
public class MatchesController {
	@Autowired
	MatchesService matchesService;
	
	@GetMapping("/matches")
	private List<Match> getAllMatches(){
		return matchesService.getAllMatches();
	
	}
	
	@GetMapping("/matches/{id}")
	private Match getMatch(@PathVariable("id") int id) {
		return matchesService.getMatchesById(id);
	}
	
	@DeleteMapping("/matches/{id}")
	private void deleteMatch(@PathVariable("id") int id) {
		matchesService.delete(id);
		
	}
	
	@PostMapping("/matches")
	private int saveMatch(@RequestBody Match match ) {
		matchesService.saveOrUpdate(match);
		return match.getMatchId();
	}
	

}
