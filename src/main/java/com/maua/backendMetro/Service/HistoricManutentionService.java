package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.rest.controller.dto.HistoricManutentionDTO;

import java.util.List;

public interface HistoricManutentionService {

    HistoricManutention createHistoricManutention(HistoricManutentionDTO dto);

    List<HistoricManutention> findHistoricManutentionByExtinguisherId(Extinguisher extinguisher);

}
