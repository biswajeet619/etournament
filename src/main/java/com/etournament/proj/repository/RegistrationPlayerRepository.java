package com.etournament.proj.repository;

import com.etournament.proj.model.RegistrationPlayer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegistrationPlayerRepository extends PagingAndSortingRepository<RegistrationPlayer, String> {
}
