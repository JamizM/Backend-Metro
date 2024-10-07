package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "historic_manutention")
public class HistoricManutention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "historic_manutention_id")
    private Long historicManutentionId;

    @NotNull
    @Column(name = "extinguisher_id")
    private String extinguisherId;

    @NotNull
    @Column(name="maintenance_date")
    private String maintenanceData;

    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="responsible")
    private String responsible;
}
