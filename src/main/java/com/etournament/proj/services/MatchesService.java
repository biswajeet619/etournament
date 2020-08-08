package com.etournament.proj.services;

import com.etournament.proj.model.Match;
import com.etournament.proj.repo.MatchesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class MatchesService {

    @Autowired
    private MatchesRepo matchesRepo;

    public List<Match> getAllMatches() {
        return matchesRepo.findAll();
    }

    public Match getMatchesById(String id) {
        Optional<Match> matchOptional = matchesRepo.findById(UUID.fromString(id));
        if (matchOptional.isPresent())
            return matchOptional.get();
        else
            throw new RuntimeException("Match Not Found");
    }

    public Match saveOrUpdate(Match match) {
        return matchesRepo.save(match);
    }


    public void delete(String id) {
        matchesRepo.deleteById(UUID.fromString(id));
    }

}
