package com.maua.backendMetro.domain.repository;
import com.maua.backendMetro.domain.entity.Extinguisher;

import com.maua.backendMetro.domain.entity.Localization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RepositoryRestResource(path = "Extinguishers")
@Repository
public interface Extinguishers extends JpaRepository<Extinguisher, String> {

    Optional<Extinguisher> findExtinguisherByLocalization(Localization localization);
}
