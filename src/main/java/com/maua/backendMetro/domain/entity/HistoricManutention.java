package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="historic_manutention")
public class HistoricManutention {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @NotNull
    @JoinColumn(name="extinguisher_id")
    private Extinguisher extinguisherId;

    @NotNull
    @Column(name="maintance_data")
    private String maintanceData;

    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="responsible")
    private String responsible;
}
