package com.maua.backendMetro.rest.controller.dto;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtinguisherDTO {
    private String id;

    private ExtinguisherType extinguisherType;

    private LocalDate expirationDate;

    private LocalDate nextInspection;

    private Integer capacity;

    private Integer teamCode;

    private String manufacturerCode;

    private LocalDate lastRechargeDate;

    private ExtinguisherStatus extinguisherStatus;

    @NotNull(message = "Localization ID is required")
    private Localization localization;

}