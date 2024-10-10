package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.rest.controller.dto.HistoricManutentionDTO;

public interface HistoricManutentionService {

    public HistoricManutention createHistoricManutention(HistoricManutentionDTO historicManutentionDTO);
}
