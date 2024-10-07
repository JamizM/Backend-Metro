package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extinguisher {
    @Id
    private String id;

    @Column(name = "extinguisher_type")
    @NotNull
    private ExtinguisherType extinguisherType;

    @Column(name = "capacity")
    @NotNull
    private Integer capacity;

    @Column(name = "manufacturer_code")
    @NotNull
    private String manufacturerCode;

    @Column(name = "expiration_date")
    @NotNull
    private String expirationDate;

    @Column(name = "last_recharge_date")
    @NotNull
    private String lastRechargeDate;

    @Column(name = "team_code")
    @NotNull
    private Integer teamCode;

    @Column(name = "next_inspection")
    @NotNull
    private String nextInspection;

    @Column(name = "extinguisher_status")
    @NotNull
    private ExtinguisherStatus extinguisherStatus;

    @ManyToOne
    @JoinColumn(name = "localization_id", nullable = false)
    private Localization localization;
}

    //qrcode
    //observacoes

