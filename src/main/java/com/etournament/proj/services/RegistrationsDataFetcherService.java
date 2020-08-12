package com.etournament.proj.services;

import com.etournament.proj.model.Registration;
import com.etournament.proj.model.RegistrationId;
import com.etournament.proj.model.RegistrationPlayer;
import com.etournament.proj.model.RegistrationStatus;
import com.etournament.proj.repo.RegistrationRepository;
import com.etournament.proj.security.service.impl.UserDetailsImpl;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistrationsDataFetcherService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Registration> getAllRegistrations(DataFetchingEnvironment dataFetchingEnvironment) {
        int count = dataFetchingEnvironment.getArgument("count");
        int page = dataFetchingEnvironment.getArgument("page");
        return registrationRepository
                .findAll(PageRequest.of(page, count))
                .toList();
    }

    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Registration> getRegistrationByMatchId(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        int count = dataFetchingEnvironment.getArgument("count");
        int page = dataFetchingEnvironment.getArgument("page");
        return registrationRepository
                .findAllByMatchId(matchId, PageRequest.of(page, count))
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Registration> getRegistrationForCurrentUser(DataFetchingEnvironment dataFetchingEnvironment) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        int count = dataFetchingEnvironment.getArgument("count");
        int page = dataFetchingEnvironment.getArgument("page");
        return registrationRepository
                .findAllByUserId(userId, PageRequest.of(page, count))
                .toList();
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Registration getRegistrationByMatchIdForCurrentUser(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();
        return registrationRepository
                .findById(new RegistrationId(matchId, userId))
                .orElse(null);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Registration createRegistration(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();

        Registration registration = new Registration();
        registration.setMatchId(matchId);
        registration.setUserId(userId);
        registration.setStatus(RegistrationStatus.PAYMENT_PENDING);

        //todo: call payment gateway to create order id

        return registrationRepository.save(registration);
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean cancelRegistration(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();

        Optional<Registration> registrationOptional = registrationRepository.findById(new RegistrationId(matchId, userId));
        if (registrationOptional.isPresent()) {
            Registration registration = registrationOptional.get();
            registration.setStatus(RegistrationStatus.CANCELLED);
            //todo: call payment gateway to refund amount using order id
            registrationRepository.save(registration);
            return true;
        } else {
            throw new RuntimeException("Registration Not found");
        }

    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Boolean updatePayment(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();

        Optional<Registration> registrationOptional = registrationRepository.findById(new RegistrationId(matchId, userId));
        if (registrationOptional.isPresent()) {
            Registration registration = registrationOptional.get();
            //todo: call payment gateway to check payment status
            registration.setStatus(RegistrationStatus.COMPLETED);
            registrationRepository.save(registration);
            return true;
        } else {
            throw new RuntimeException("Registration Not found");
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Registration addPlayer(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        String playerUsername = dataFetchingEnvironment.getArgument("playerUsername");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();

        Optional<Registration> registrationOptional = registrationRepository.findById(new RegistrationId(matchId, userId));
        if (registrationOptional.isPresent()) {
            Registration registration = registrationOptional.get();
            registration.getPlayers().add(new RegistrationPlayer(playerUsername));
            return registrationRepository.save(registration);
        } else {
            throw new RuntimeException("Registration Not found");
        }
    }


    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Registration removePlayer(DataFetchingEnvironment dataFetchingEnvironment) {
        String matchId = dataFetchingEnvironment.getArgument("matchId");
        String playerUsername = dataFetchingEnvironment.getArgument("playerUsername");
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userId = userDetails.getId();

        Optional<Registration> registrationOptional = registrationRepository.findById(new RegistrationId(matchId, userId));
        if (registrationOptional.isPresent()) {
            Registration registration = registrationOptional.get();
            registration.getPlayers().remove(new RegistrationPlayer(playerUsername));
            return registrationRepository.save(registration);
        } else {
            throw new RuntimeException("Registration Not found");
        }
    }
}
