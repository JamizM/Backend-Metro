package com.maua.backendMetro.entity;

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

    @NotNull
    @JoinColumn(name="localization_id")
    private String extinguisherId;

    @NotNull
    @Column(name="maintance_data")
    private String maintanceData;

    @Column(name="description")
    private String description;

    @NotNull
    @Column(name="responsible")
    private String responsible;
}
