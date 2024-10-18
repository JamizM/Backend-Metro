package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.HistoricManutentionService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HistoricManutentionImpl implements HistoricManutentionService {

    @Autowired
    private HistoricManutentions historicManutentions;
    @Autowired
    private Extinguishers extinguishers;

    @Override
    public List<HistoricManutention> getHistoricManutentionByExtinguisherId(String extinguisherId) {
        Optional<Extinguisher> optionalExtinguisher = extinguishers.findById(extinguisherId);

        if (optionalExtinguisher.isPresent()) {
            return historicManutentions.findHistoricManutentionByExtinguisherId(extinguisherId);
        }

        return null;
    }
}
