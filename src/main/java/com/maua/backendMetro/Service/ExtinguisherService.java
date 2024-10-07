package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;

import java.util.List;

public interface ExtinguisherService {

    List<Extinguisher> expirationAlert(ExtinguisherDTO extinguisherDTO);

    List<Extinguisher> nextInspectionAlert(ExtinguisherDTO extinguisherDTO);
}
