package com.etournament.proj.fetchers;

import com.etournament.proj.model.Match;
import com.etournament.proj.services.DataFetcherService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private DataFetcherService dataFetcherService;

    public DataFetcher<List<Match>> getAllMatches() {
        return dataFetchingEnvironment -> dataFetcherService.getAllMatches(dataFetchingEnvironment);
    }

    public DataFetcher<Match> getMatch() {
        return dataFetchingEnvironment -> dataFetcherService.getMatch(dataFetchingEnvironment);
    }

    public DataFetcher<Match> createMatch() {
        return dataFetchingEnvironment -> dataFetcherService.createMatch(dataFetchingEnvironment);
    }

    public DataFetcher<Boolean> deleteMatch() {
        return dataFetchingEnvironment -> dataFetcherService.deleteMatch(dataFetchingEnvironment);
    }
}
