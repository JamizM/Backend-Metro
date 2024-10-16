package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.HistoricManutentionService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.rest.controller.dto.HistoricManutentionDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HistoricManutentionImpl implements HistoricManutentionService {

    @Autowired
    private HistoricManutentions historicManutentions;

    @Autowired
    private Extinguishers extinguishers;

    public HistoricManutention createHistoricManutention(HistoricManutentionDTO dto) {
        Extinguisher extinguisher = extinguishers.findById(dto.getExtinguisher())
                .orElseThrow(() -> new RuntimeException("Extinguisher not found with ID: " + dto.getExtinguisher()));

        HistoricManutention historicManutention = new HistoricManutention();
        historicManutention.setMaintenanceData(dto.getMaintenanceData());
        historicManutention.setDescription(dto.getDescription());
        historicManutention.setResponsible(dto.getResponsible());
        historicManutention.setExtinguisher(extinguisher);

        return historicManutentions.save(historicManutention);
    }

    @Override
    public List<HistoricManutention> findHistoricManutentionByExtinguisherId(Extinguisher extinguisher) {
        return historicManutentions.queryHistoricManutentionByExtinguisherId(extinguisher.getId());
    }

}
