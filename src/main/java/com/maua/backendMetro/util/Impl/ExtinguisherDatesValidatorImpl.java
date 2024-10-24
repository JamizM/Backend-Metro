package com.maua.backendMetro.util.Impl;

import com.maua.backendMetro.domain.entity.Extinguisher;

import com.maua.backendMetro.util.ValidExtinguisherDates;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ExtinguisherDatesValidatorImpl implements ConstraintValidator<ValidExtinguisherDates, Extinguisher> {

    @Override
    public boolean isValid(Extinguisher extinguisher, ConstraintValidatorContext context) {
        if (extinguisher == null) {
            return true;
        }

        LocalDate expirationDate = extinguisher.getExpirationDate();
        LocalDate lastRechargeDate = extinguisher.getLastRechargeDate();
        LocalDate nextInspection = extinguisher.getNextInspection();

        // Verifica se a data de última recarga é anterior ou igual à data de expiração e à próxima inspeção
        boolean validExtinguisher = lastRechargeDate != null && expirationDate != null &&
                lastRechargeDate.isBefore(expirationDate) &&
                nextInspection != null &&
                lastRechargeDate.isBefore(nextInspection) &&
                nextInspection.isBefore(expirationDate)
                ;

        return validExtinguisher;
    }
}

