package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.HistoricManutentionService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.rest.controller.dto.HistoricManutentionDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class HistoricManutentionImpl implements HistoricManutentionService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private HistoricManutentions historicManutentions;

    public HistoricManutention createHistoricManutention(HistoricManutentionDTO dto) {
        Extinguisher extinguisher = entityManager.getReference(Extinguisher.class, dto);

        HistoricManutention historicManutention = new HistoricManutention();

        historicManutention.setExtinguisher(extinguisher);

        historicManutention.setMaintenanceData(LocalDate.now());
        historicManutention.setDescription("Manutenção realizada");
        historicManutention.setResponsible("João da Silva");

        historicManutentions.save(historicManutention);
        return historicManutention;
    }
}
