package com.maua.backendMetro.rest.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricManutentionDTO {
    private Long idManutention;
    private String maintenanceData;
    private String description;
    private String responsible;
    @NotNull(message = "Extinguisher ID is required")
    private String extinguisherId;
}
