package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import com.maua.backendMetro.util.MessageWriterEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface ExtinguisherService {

    List<Extinguisher> nextInspectionOfExtinguisherAlert(String id);

    Extinguisher createExtinguisher(ExtinguisherDTO extinguisherDTO);

    Optional<Extinguisher> findExtinguisherByLocalizationDetails(
            MetroLine area,
            SubwayStation subwayStation,
            @RequestParam String detailedLocation
    );

    List<String> verifyExpirationDateExtinguisherAndAlert() throws MessageWriterEntity;

}
