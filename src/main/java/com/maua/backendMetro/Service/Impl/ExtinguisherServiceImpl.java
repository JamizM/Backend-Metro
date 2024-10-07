package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.domain.repository.Users;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import com.maua.backendMetro.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ExtinguisherServiceImpl implements ExtinguisherService {

    private final Extinguishers extinguishers;
    private final Localizations localizations;
    private final Users users;

    @Override
    @Transactional
    public List<Extinguisher> expirationAlert(ExtinguisherDTO extinguisherDTO) {
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
    public List<Extinguisher> nextInspectionAlert(ExtinguisherDTO extinguisherDTO) {
        return List.of();
    }

    @Override
    public Extinguisher createExtinguisher(ExtinguisherDTO extinguisherDTO) {
        Localization localization = localizations.findById(extinguisherDTO.getLocalizationId())
                .orElseThrow(() -> new EntityNotFoundException("Localization not found"));

        Extinguisher extinguisher = new Extinguisher();
        extinguisher.setLocalization(localization);

        return extinguishers.save(extinguisher);
    }
}