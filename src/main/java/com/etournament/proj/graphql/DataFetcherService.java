package com.etournament.proj.graphql;

import com.etournament.proj.model.Match;
import com.etournament.proj.repo.MatchesRepo;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DataFetcherService {

    @Autowired
    private MatchesRepo matchRepository;

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Match> getAllMatches(DataFetchingEnvironment dataFetchingEnvironment) {
        int count = dataFetchingEnvironment.getArgument("count");
        int page = dataFetchingEnvironment.getArgument("page");
        return matchRepository
                .findAll()
                .stream()
                .skip(count * page)
                .limit(count)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Match getMatch(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        return matchRepository
                .findById(UUID.fromString(matchId))
                .orElse(null);
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public Match createMatch(DataFetchingEnvironment dataFetchingEnvironment) {
        double matchFees = dataFetchingEnvironment.getArgument("matchFees");
        double matchPrize = dataFetchingEnvironment.getArgument("matchPrize");
        String matchName = dataFetchingEnvironment.getArgument("matchName");
        String squadCriteria = dataFetchingEnvironment.getArgument("squadCriteria");
        String matchPP = dataFetchingEnvironment.getArgument("matchPP");
        LocalDateTime matchDate = LocalDateTime.parse(dataFetchingEnvironment.<String>getArgument("matchDate"));
        String matchMap = dataFetchingEnvironment.getArgument("matchMap");
        String matchPhotoImg = dataFetchingEnvironment.getArgument("matchPhotoImg");
        Match match = new Match(
                matchFees,
                matchPrize,
                matchName,
                squadCriteria,
                matchPP,
                matchDate,
                matchMap,
                matchPhotoImg
        );
        return matchRepository.save(match);
    }
}
