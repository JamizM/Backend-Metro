package com.maua.backendMetro.Service;

import com.google.zxing.WriterException;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import com.maua.backendMetro.util.MessageWriterEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExtinguisherService {

    Extinguisher createExtinguisher(ExtinguisherDTO extinguisherDTO);

    Optional<Extinguisher> findExtinguisherByLocalizationDetails(
            MetroLine area,
            SubwayStation subwayStation,
            @RequestParam String detailedLocation
    );

    List<String> verifyExpirationDateExtinguisherAndAlert() throws MessageWriterEntity;

    List<Extinguisher> findExtinguisherByExtinguisherStatus(@RequestParam ExtinguisherStatus extinguisherStatus);

    List<String> scheduleRegularInspectionsOfExtinguishers(String extinguisherId, int months);

    byte[] generateQRCodeForExtinguisher(String extinguisherId) throws IOException, WriterException;

    void logDeletion(String extinguisherId, String userName, String reason);
}
