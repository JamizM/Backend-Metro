package com.maua.backendMetro.domain.repository;
import com.maua.backendMetro.domain.entity.Localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "Localizations")
@Repository
public interface Localizations  extends JpaRepository<Localization, Integer> {
}
