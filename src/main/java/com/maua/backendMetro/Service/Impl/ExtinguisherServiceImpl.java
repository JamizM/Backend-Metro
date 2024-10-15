package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExtinguisherServiceImpl implements ExtinguisherService {

    private final Extinguishers extinguishers;
    private final Localizations localizations;

    @Override
    @Transactional
    public List<Extinguisher> nextInspectionOfExtinguisherAlert(String id) {
        return null;
    }

    @Override
    public Extinguisher createExtinguisher(@NotNull ExtinguisherDTO extinguisherDTO) {
        Localization localization = localizations.findById(extinguisherDTO.getLocalizationId())
                .orElseThrow(() -> new EntityNotFoundException("Localization not found"));

        Extinguisher extinguisher = new Extinguisher();
        extinguisher.setLocalization(localization);

        return extinguishers.save(extinguisher);
    }

    //regras de negocio
    @Override
    public Optional<Extinguisher> findExtinguisherByLocalizationDetails(MetroLine area, SubwayStation subwayStation, @RequestParam(required = false) String detailedLocation) {
        return extinguishers.findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation(area, subwayStation, detailedLocation);
    }

    @Override
    public void verifyIfNextInspectionIsLessThanExpirationDate(Extinguisher extinguisher) {
        LocalDate expirationDateExtinguisher = extinguisher.getExpirationDate();
        LocalDate nextInspectionExtinguisher = extinguisher.getNextInspection();

        //fazer funcao que retorna o id do extintor, porem este codigo funciona mesmo assim, dando erro 500
        if (nextInspectionExtinguisher.isAfter(expirationDateExtinguisher)){ //se a data de inspecao for depois da data de expiracao, será lançada exceção
            throw new IllegalArgumentException("The next inspection date cannot be after the expiration date, verify the exintiguisher with ID: " + extinguisher.getId());
        }
    }

    @Override
    public List<String> verifyExpirationDateExtinguisherAndAlert() {
        List<Extinguisher> extinguisherList = extinguishers.findAll();

        if (extinguisherList.isEmpty()) {
            return List.of("No extinguishers found.");
        }

        LocalDate currentDate = LocalDate.now();
        List<String> messages = new ArrayList<>();

        for (Extinguisher extinguisher : extinguisherList) {

            verifyIfNextInspectionIsLessThanExpirationDate(extinguisher);

            LocalDate expirationDate = extinguisher.getExpirationDate();
            LocalDate nextInspectionDate = extinguisher.getNextInspection();

            if(currentDate.isAfter(nextInspectionDate) || currentDate.isEqual(nextInspectionDate)) {
                messages.add("The Extinguisher " + extinguisher.getId() + " is due for inspection, day: " + nextInspectionDate);
            }

            if (currentDate.isAfter(expirationDate) || currentDate.isEqual(expirationDate)) {
                messages.add("The Extinguisher with ID " + extinguisher.getId() + " has expired.");
            }
            else if (currentDate.plusMonths(12).isBefore(expirationDate)) {
                messages.add("The E xtinguisher with ID " + extinguisher.getId() + " is within the expiration date.");
            }
        }
        return messages;
    }

    @Override
    public List<Extinguisher> findExtinguisherByExtinguisherStatus(ExtinguisherStatus extinguisherStatus) {
        return extinguishers.findExtinguisherByExtinguisherStatus(extinguisherStatus);
    }
}