package com.maua.backendMetro.rest.controller.dto;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtinguisherDTO {
    private String id;

    private String expirationDate;

    private String nextInspection;

    private ExtinguisherStatus extinguisherStatus;

    @NotNull(message = "Localization ID is required")
    private Long localizationId;

}