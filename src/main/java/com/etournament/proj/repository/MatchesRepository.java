package com.etournament.proj.repository;


import com.etournament.proj.model.Match;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;


public interface MatchesRepository extends PagingAndSortingRepository<Match, UUID> {


}

