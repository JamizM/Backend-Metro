package com.maua.backendMetro.repository;
import com.maua.backendMetro.entity.HistoricManutention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "HistoricManutentions")
public interface HistoricManutentions  extends JpaRepository<HistoricManutention, Integer> {
}
