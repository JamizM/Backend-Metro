package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
public class HistoricManutention {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManutention;

    @ManyToOne
    @JoinColumn(name = "id_extinguisher")
    private Extinguisher extinguisher;

    @Column(name="maintenance_date", nullable = false)
    @PastOrPresent(message = "Maintenance Date must be in the past or present")
    private LocalDate maintenanceDate;

    @Column(name="description")
    private String description;

    @Column(name="responsible", nullable = false)
    private String responsible;
}
