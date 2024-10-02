package com.maua.backendMetro.domain.repository;
import com.maua.backendMetro.domain.entity.Extinguisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Extinguishers")
public interface Extinguishers  extends JpaRepository<Extinguisher, Integer> {
}
