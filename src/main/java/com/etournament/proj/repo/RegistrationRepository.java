package com.etournament.proj.repo;

import com.etournament.proj.model.Registration;
import com.etournament.proj.model.RegistrationId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegistrationRepository extends PagingAndSortingRepository<Registration, RegistrationId> {
    Page<Registration> findAllByMatchId(String matchId, Pageable pageable);

    Page<Registration> findAllByUserId(String userId, Pageable pageable);
}
