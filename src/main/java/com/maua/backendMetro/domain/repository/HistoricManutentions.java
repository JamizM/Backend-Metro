package com.maua.backendMetro.domain.repository;
import com.maua.backendMetro.domain.entity.HistoricManutention;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "HistoricManutentions")
@Repository
public interface HistoricManutentions extends JpaRepository<HistoricManutention, Integer> {
}
