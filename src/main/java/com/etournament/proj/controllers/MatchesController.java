package com.etournament.proj.controllers;

import com.etournament.proj.model.Match;
import com.etournament.proj.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchesController {

    @Autowired
    private MatchesService matchesService;

    @GetMapping
    private List<Match> getAllMatches() {
        return matchesService.getAllMatches();
    }

    @GetMapping("/{id}")
    private Match getMatch(@PathVariable("id") String id) {
        return matchesService.getMatchesById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteMatch(@PathVariable("id") String id) {
        matchesService.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Match saveMatch(@RequestBody Match match) {
        return matchesService.saveOrUpdate(match);
    }


}
