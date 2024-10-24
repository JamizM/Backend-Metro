package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.HistoricManutention;

import java.util.List;

public interface HistoricManutentionService {

    List<HistoricManutention> getHistoricManutentionByExtinguisherId(String extinguisherId);

}
