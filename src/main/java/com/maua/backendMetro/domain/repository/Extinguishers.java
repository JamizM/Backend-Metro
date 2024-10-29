package com.maua.backendMetro.domain.repository;
import com.maua.backendMetro.domain.entity.Extinguisher;

import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(path = "Extinguishers")
@Repository
public interface Extinguishers extends JpaRepository<Extinguisher, String> {

    Optional<Extinguisher> findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation
            (
            MetroLine area,
            SubwayStation subwayStation,
            @RequestParam String detailedLocation
            );

    List<Extinguisher> findExtinguisherByExtinguisherStatus(ExtinguisherStatus extinguisherStatus);

    List<Extinguisher> findExtinguisherByExtinguisherType(ExtinguisherType extinguisherType);
}
