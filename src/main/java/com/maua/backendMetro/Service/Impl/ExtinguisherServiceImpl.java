package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.domain.repository.Users;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import com.maua.backendMetro.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExtinguisherServiceImpl implements ExtinguisherService {

    private final Extinguishers extinguishers;
    private final Localizations localizations;

    @Override
    @Transactional
    public List<Extinguisher> expirationAlert(@NotNull ExtinguisherDTO extinguisherDTO) {
        String exinguisherId = extinguisherDTO.getId();
        Extinguisher extinguisherDB = extinguishers.findById(exinguisherId)
                .orElseThrow(() -> new RegraNegocioException("Extintor não encontrado"));

        String expirationDateExtinguisher = extinguisherDTO.getExpirationDate();
        if(expirationDateExtinguisher.isEmpty()){
            throw new RegraNegocioException("Data de validade do extintor não informada");
        }

        String nextInspectionExtinguisher = extinguisherDTO.getNextInspection();
        if (nextInspectionExtinguisher.isEmpty()) {
            throw new RegraNegocioException("Data da próxima inspeção do extintor não informada");
        } else if (nextInspectionExtinguisher.compareTo(expirationDateExtinguisher) > 0) {
            throw new RegraNegocioException("Data da próxima inspeção do extintor não pode ser maior que a data de validade");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expirationDate = LocalDate.parse(expirationDateExtinguisher, formatter);
        LocalDate currentDate = LocalDate.now();

        long monthsBetween = ChronoUnit.MONTHS.between(currentDate, expirationDate);
        if (monthsBetween <= 12) {
            throw new RegraNegocioException("A data de validade do extintor está a 12 meses ou menos de vencer");
        }

        return List.of(extinguisherDB);
    }

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

    public Optional<Extinguisher> findExtinguisherByLocalizationDetails(MetroLine area, SubwayStation subwayStation, @RequestParam(required = false) String detailedLocation) {
        return extinguishers.findExtinguisherByLocalization_AreaAndLocalization_SubwayStationAndLocalization_DetailedLocation(area, subwayStation, detailedLocation);
    }
}