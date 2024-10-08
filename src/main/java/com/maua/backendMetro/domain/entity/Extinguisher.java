package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Extinguisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(name = "extinguisher_type")
    @NotNull
    private ExtinguisherType extinguisherType;

    @Column(name = "capacity")
    @NotNull
    private Integer capacity;

    @Column(name = "manufacturer_code")
    @NotNull
    private LocalDate manufacturerCode;

    @Column(name = "expiration_date")
    @NotNull
    private LocalDate expirationDate;

    @Column(name = "last_recharge_date")
    @NotNull
    private LocalDate lastRechargeDate;

    @Column(name = "team_code")
    @NotNull
    private Integer teamCode;

    @Column(name = "next_inspection")
    @NotNull
    private LocalDate nextInspection;

    @Column(name = "extinguisher_status")
    @NotNull
    private ExtinguisherStatus extinguisherStatus;

    @ManyToOne
    @JoinColumn(name = "id_localization", nullable = false)
    private Localization localization;
}

    //qrcode
    //observacoes

