package com.maua.backendMetro.domain.repository;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ExtinguishersTest {

    @Autowired
    EntityManager entityManager;

    @Test
    void findExtinguisherByLocalization() {

    }
}