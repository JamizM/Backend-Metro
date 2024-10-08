package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoricManutention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "extinguisher_id")
    private Extinguisher extinguisher;

    @NotNull
    @Column(name="maintenance_date")
    private LocalDate maintenanceData;

    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="responsible")
    private String responsible;
}
