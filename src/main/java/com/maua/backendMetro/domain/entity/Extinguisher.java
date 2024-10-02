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
@Table(name="extinguishers")
public class Extinguisher {
    @Id
    private String extinguisherId;

    @Column(name="extinguisher_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ExtinguisherType extinguisherType;

    @Column(name="capacity")
    @NotNull
    private Integer capacity;

    @Column(name="manufacturer_code")
    @NotNull
    private String manufacturerCode;

    @Column(name="expiration_date")
    @NotNull
    private String expirationDate;

    @Column(name="lastRecharge_date")
    private String lastRechargeDate;

    @Column(name="next_inspection")
    private String  nextInspection;

    @Column(name="extinguisher_status")
    @Enumerated(EnumType.STRING)
    @NotNull
    private ExtinguisherStatus extinguisherStatus;

    @JoinColumn(name="localization_id")
    @NotNull
    private Integer localizationId; // chave estrangeira para a tabela de localização

    //qrcode
    //observacoes

}
