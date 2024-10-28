package com.maua.backendMetro.domain.repository;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import java.lang.IllegalArgumentException;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ExtinguishersTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    Extinguishers extinguishers;

    //testes do repository
    private Extinguisher createExtinguisher(Extinguisher extinguisher) {
        Extinguisher newExtinguisher = new Extinguisher(extinguisher);
        this.entityManager.persist(newExtinguisher);
        return newExtinguisher;
    }

    private Localization createLocalization(Localization localization){
        Localization newLocalization = new Localization(localization);
        this.entityManager.persist(newLocalization);
        return newLocalization;
    }

    @Test
    @DisplayName("Should return a extinguisher by localization area, subway station and detailed location")
    void findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation_Success() {
        Localization localization = new Localization("RED_LINE", "JABAQUARA", "Plataforma 1");

        entityManager.persist(localization);

        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");
        extinguisher.setLocalization(localization);

        createExtinguisher(extinguisher);

        Optional<Extinguisher> foundedExtinguisherByLocalization = extinguishers.findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation(
                localization.getArea(),
                localization.getSubwayStation(),
                localization.getDetailedLocation());

        assertThat(foundedExtinguisherByLocalization.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not return a extinguisher by localization area, subway station and detailed location")
    void findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation_Failed() {
        Localization localization = new Localization("RED_LINE", "JABAQUARA", "Plataforma 1");

        entityManager.persist(localization);

        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");
        //não setamos localização para o extintor

        createExtinguisher(extinguisher);

        Optional<Extinguisher> foundedExtinguisherByLocalization = extinguishers.findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation(
                localization.getArea(),
                localization.getSubwayStation(),
                localization.getDetailedLocation());

        assertThat(foundedExtinguisherByLocalization.isEmpty()).isTrue();
    }


    @Test
    @DisplayName("Should return status of extinguisher")
    void findExtinguisherByExtinguisherStatus_Sucess() {
        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");

        createExtinguisher(extinguisher);

        Optional<Extinguisher> foundedExtinguisherByStatus = extinguishers.
                findExtinguisherByExtinguisherStatus(extinguisher.getExtinguisherStatus()).stream().findFirst();

        assertThat(foundedExtinguisherByStatus.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException for invalid extinguisher status")
    void findExtinguisherByExtinguisherStatus_Failed() {
        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");

        createExtinguisher(extinguisher);

        assertThat(extinguishers.findExtinguisherByExtinguisherStatus(extinguisher.getExtinguisherStatus()).isEmpty()).isFalse();
        assertThrows(IllegalArgumentException.class, () -> {
            ExtinguisherStatus.valueOf("INVALID_STATUS");
        });
    }

    @Test
    @DisplayName("Should return extinguisher by extinguisher type")
    void findExtinguisherByExtinguisherType_Sucess() {
        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");

        createExtinguisher(extinguisher);

        Optional<Extinguisher> foundedExtinguisherByType = extinguishers.
                findExtinguisherByExtinguisherType(extinguisher.getExtinguisherType()).stream().findFirst();

        assertThat(foundedExtinguisherByType.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should return extinguisher by extinguisher type")
    void findExtinguisherByExtinguisherType_Failed() {
        Extinguisher extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");

        createExtinguisher(extinguisher);

        assertThat(extinguishers.findExtinguisherByExtinguisherType(extinguisher.getExtinguisherType()).isEmpty()).isFalse();
        assertThrows(IllegalArgumentException.class, () -> {
            ExtinguisherType.valueOf("INVALID_STATUS");
        });
    }
}