package com.etournament.proj.fetchers;

import com.etournament.proj.model.Match;
import com.etournament.proj.model.Registration;
import com.etournament.proj.services.MatchesDataFetcherService;
import com.etournament.proj.services.RegistrationsDataFetcherService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private MatchesDataFetcherService matchesDataFetcherService;

    @Autowired
    private RegistrationsDataFetcherService registrationsDataFetcherService;

    public DataFetcher<List<Match>> getAllMatches() {
        return dataFetchingEnvironment -> matchesDataFetcherService.getAllMatches(dataFetchingEnvironment);
    }

    public DataFetcher<Match> getMatch() {
        return dataFetchingEnvironment -> matchesDataFetcherService.getMatch(dataFetchingEnvironment);
    }

    public DataFetcher<Match> createMatch() {
        return dataFetchingEnvironment -> matchesDataFetcherService.createMatch(dataFetchingEnvironment);
    }

    public DataFetcher<Boolean> deleteMatch() {
        return dataFetchingEnvironment -> matchesDataFetcherService.deleteMatch(dataFetchingEnvironment);
    }

    public DataFetcher<List<Registration>> getAllRegistrations() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.getAllRegistrations(dataFetchingEnvironment);
    }

    public DataFetcher<List<Registration>> getRegistrationByMatch() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.getRegistrationByMatchId(dataFetchingEnvironment);
    }

    public DataFetcher<List<Registration>> getRegistrationForUser() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.getRegistrationForCurrentUser(dataFetchingEnvironment);
    }

    public DataFetcher<Registration> getRegistrationByMatchForUser() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.getRegistrationByMatchIdForCurrentUser(dataFetchingEnvironment);
    }

    public DataFetcher<Registration> createRegistration() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.createRegistration(dataFetchingEnvironment);
    }

    public DataFetcher<Boolean> cancelRegistration() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.cancelRegistration(dataFetchingEnvironment);
    }

    public DataFetcher<Boolean> updatePayment() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.updatePayment(dataFetchingEnvironment);
    }

    public DataFetcher<Registration> addPlayer() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.addPlayer(dataFetchingEnvironment);
    }

    public DataFetcher<Registration> removePlayer() {
        return dataFetchingEnvironment -> registrationsDataFetcherService.removePlayer(dataFetchingEnvironment);
    }
}
