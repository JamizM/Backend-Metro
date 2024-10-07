package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "extinguisher")
public class Extinguisher {
    @Id
    @Column(name = "extinguisher_id")
    private String extinguisherId;

    @Column(name = "extinguisher_type")
    @NotNull
    private ExtinguisherType extinguisherType;

    @Column(name = "capacity")
    @NotNull
    private int capacity;

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
    private int teamCode;

    @Column(name = "next_inspection")
    @NotNull
    private String nextInspection;

    @Column(name = "extinguisher_status")
    @NotNull
    private ExtinguisherStatus extinguisherStatus;

    @ManyToOne
    @JoinColumn(name = "localization_id", referencedColumnName = "LocalizationId")
    private Localization localization; //// chave estrangeira para a tabela de localização
}

    //qrcode
    //observacoes

