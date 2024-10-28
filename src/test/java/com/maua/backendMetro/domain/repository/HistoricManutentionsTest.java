package com.maua.backendMetro.domain.repository;


import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import jakarta.persistence.EntityManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ActiveProfiles("test")
public class HistoricManutentionsTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    HistoricManutentions historicManutentions;

    private Extinguisher extinguisher;

    private HistoricManutention historicManutention;

    @BeforeEach
    void setUp() {
        extinguisher = new Extinguisher("0EX143-4442",
                "FOAM",
                10,
                "GIEFL",
                "2025-12-01",
                "2023-01-01",
                123,
                "2025-01-01",
                "OK");

        entityManager.persist(extinguisher);

        historicManutention = new HistoricManutention(extinguisher,
                "2023-12-12",
                "Recarga",
                "Matheus Yudi Chinen Oliveira");
    }

    private @NotNull HistoricManutention createHistoricManutention(HistoricManutention historicManutention) {
        HistoricManutention newHistoricManutention = new HistoricManutention(historicManutention);
        this.entityManager.persist(newHistoricManutention);
        return newHistoricManutention;
    }

    @Test
    @DisplayName("Should return historic manutention by extinguisher id")
    void testFindByExtinguisherId_Sucess() {
        HistoricManutention historicManutention = createHistoricManutention(this.historicManutention);

        HistoricManutention historicManutentionByExtinguisherId = historicManutentions.
                findHistoricManutentionByExtinguisherId(historicManutention.getExtinguisher().getId()).getFirst();

        assertEquals(historicManutention, historicManutentionByExtinguisherId);
    }

}
