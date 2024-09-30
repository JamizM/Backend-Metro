package com.maua.backendMetro.repository;
import com.maua.backendMetro.entity.Localization;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Localizations")
public interface Localizations  extends JpaRepository<Localization, Integer> {
}
