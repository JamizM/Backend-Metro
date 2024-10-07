package com.maua.backendMetro.Service.Impl;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.domain.repository.Users;
import com.maua.backendMetro.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor //gera contrutor com argumentos final
@Service
public class ExtinguisherServiceImpl implements ExtinguisherService {

    private final Extinguishers extinguishers;
    private final Localizations localizations;
    private final Users users;

    @Override
    @Transactional
    public List<Extinguisher> expirationAlert(Extinguisher extinguisher) {
        String exinguisherId = extinguisher.getExtinguisherId();
        Extinguisher extinguisherDB = extinguishers.findById(exinguisherId)
                .orElseThrow(() -> new RegraNegocioException("Extintor não encontrado"));

        String expirationDateExtinguisher = extinguisher.getExpirationDate();
        if(expirationDateExtinguisher.isEmpty()){
            throw new RegraNegocioException("Data de validade do extintor não informada");
        }

        String nextInspectionExtinguisher = extinguisher.getNextInspection();
        if (nextInspectionExtinguisher.isEmpty()) {
            throw new RegraNegocioException("Data da próxima inspeção do extintor não informada");
        } else if (nextInspectionExtinguisher.compareTo(expirationDateExtinguisher) > 0) { //verificamos se a data da próxima inspeção lexicograficamente
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
    public List<Extinguisher> nextInspectionAlert(Extinguisher extinguisher) {
        return List.of();
    }
}
