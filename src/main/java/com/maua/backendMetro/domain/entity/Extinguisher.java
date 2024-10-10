package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
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
public class Extinguisher {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "extinguisher_type", nullable = false)
    private ExtinguisherType extinguisherType;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "manufacturer_code", nullable = false)
    private LocalDate manufacturerCode;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "last_recharge_date", nullable = false)
    private LocalDate lastRechargeDate;

    @Column(name = "team_code", nullable = false)
    private Integer teamCode;

    @Column(name = "next_inspection", nullable = false)
    private LocalDate nextInspection;

    @Column(name = "extinguisher_status", nullable = false)
    private ExtinguisherStatus extinguisherStatus;

    @ManyToOne
    @JoinColumn(name = "id_localization")
    private Localization localization;

    @OneToMany(mappedBy = "extinguisher", fetch = FetchType.LAZY)
    private List<HistoricManutention> historicManutention;
}

    //qrcode
    //observacoes

