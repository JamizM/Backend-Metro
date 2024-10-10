package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoricManutention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutention;

    @ManyToOne
    @JoinColumn(name = "id_extinguisher")
    private Extinguisher extinguisher;

    @Column(name="maintenance_date", nullable = false)
    private LocalDate maintenanceData;

    @Column(name="description")
    private String description;

    @Column(name="responsible", nullable = false)
    private String responsible;
}
