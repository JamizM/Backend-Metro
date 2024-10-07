package com.maua.backendMetro.rest.controller.dto;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtinguisherDTO {
    private String extinguisherId;
    private String expirationDate;
    private String nextInspection;
    private ExtinguisherStatus extinguisherStatus;
    private Localization localization;
}