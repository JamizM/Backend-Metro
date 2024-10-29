package com.maua.backendMetro.Service.Impl;

import com.google.zxing.WriterException;
import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.Service.QRCodeService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Service
public class ExtinguisherServiceImpl implements ExtinguisherService {

    private final Extinguishers extinguishers;
    private final Localizations localizations;
    private final HistoricManutentions historicManutentions;
    private final QRCodeService qrCodeService;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ExtinguisherServiceImpl(Extinguishers extinguishers, Localizations localizations, HistoricManutentions historicManutentions, @Qualifier("QRCodeServiceImpl") QRCodeService qrCodeService) {
        this.extinguishers = extinguishers;
        this.localizations = localizations;
        this.historicManutentions = historicManutentions;
        this.qrCodeService = qrCodeService;
    }

    @Override
    @Transactional
    public Extinguisher createExtinguisher(@NotNull ExtinguisherDTO extinguisherDTO) {
        Localization localization = localizations.findById(extinguisherDTO.getLocalization().getId())
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
    @Transactional
    public List<String> verifyExpirationDateExtinguisherAndAlert() {
        List<Extinguisher> extinguisherList = extinguishers.findAll();

        if (extinguisherList.isEmpty()) {
            return List.of("No extinguishers found.");
        }

        LocalDate currentDate = LocalDate.now();
        List<String> messages = new ArrayList<>();

        for (Extinguisher extinguisher : extinguisherList) {

            LocalDate expirationDate = extinguisher.getExpirationDate();

            if (currentDate.isAfter(expirationDate) || currentDate.isEqual(expirationDate)) {
                messages.add("The Extinguisher with ID " + extinguisher.getId() + " has expired.");
            }
            else if (currentDate.plusMonths(12).isBefore(expirationDate)) {
                messages.add("The Extinguisher with ID " + extinguisher.getId() + " is within the expiration date.");
            }
        }
        return messages;
    }

    @Override
    public List<Extinguisher> findExtinguisherByExtinguisherStatus(ExtinguisherStatus extinguisherStatus) {
        return extinguishers.findExtinguisherByExtinguisherStatus(extinguisherStatus);
    }

    @Override
    public List<String> scheduleRegularInspectionsOfExtinguishers(String extinguisherId) {
        Optional<Extinguisher> optionalExtinguisher = extinguishers.findById(extinguisherId);
        List<String> messages = new ArrayList<>();

        if (optionalExtinguisher.isPresent()) {
            Extinguisher extinguisher = optionalExtinguisher.get();
            LocalDate nextInspectionDate = extinguisher.getNextInspection();
            LocalDate currentDate = LocalDate.now();

            if (nextInspectionDate.isAfter(currentDate.plusMonths(12))) {
                messages.add("The extinguisher ID: " + extinguisher.getId() + " is not due for inspection.");
            } else {
                extinguisher.setNextInspection(nextInspectionDate.plusMonths(12));
                extinguishers.save(extinguisher);
                messages.add("Scheduled next inspection for extinguisher ID: " + extinguisher.getId() + " on " + extinguisher.getNextInspection());
            }
        } else {
            messages.add("Extinguisher with ID: " + extinguisherId + " not found");
        }
        return messages;
    }

    public byte[] generateQRCodeForExtinguisher(String extinguisherId) throws WriterException, IOException {
        StringBuilder qrText = new StringBuilder();

        Extinguisher extinguisher = extinguishers.findById(extinguisherId)
                .orElseThrow(() -> new RuntimeException("Extintor não encontrado com ID: " + extinguisherId));

        qrText.append("ID: ").append(extinguisher.getId());

        List<HistoricManutention> manutentions = historicManutentions.findHistoricManutentionByExtinguisherId(extinguisherId);

        for (HistoricManutention manutention : manutentions) {
            qrText.append("\n\nData: ").append(manutention.getMaintenanceDate())
            .append("\nDescricao: ").append(manutention.getDescription())
            .append("\nResponsavel: ").append(manutention.getResponsible());
        }


        return qrCodeService.generateAndSaveQRCodeWithExtintorId(qrText.toString(), 350, 350);
    }
}