package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.ExtinguisherStatus;
import com.maua.backendMetro.domain.entity.enums.ExtinguisherType;
import com.maua.backendMetro.util.ValidExtinguisherDates;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Validated
@Entity
@ValidExtinguisherDates
@AllArgsConstructor

public class Extinguisher {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "extinguisher_type", nullable = false)
    @NotNull(message = "{field.extinguisher-type.required}")
    private ExtinguisherType extinguisherType;

    @Column(name = "capacity", nullable = false)
    @NotNull(message = "{field.capacity.required}")
    private Integer capacity;

    @Column(name = "manufacturer_code", nullable = false)
    @NotNull(message = "{field.manufacturer-code.required}")
    private String manufacturerCode;

    @Column(name = "expiration_date", nullable = false)
    @NotNull(message = "{field.expiration-date.required}")
    @Future(message = "Expiration Date must be in the future")
    private LocalDate expirationDate;

    @Column(name = "last_recharge_date", nullable = false)
    @NotNull(message = "{field.last-recharge-date.required}")
    @PastOrPresent(message = "Last Recharge Date must be in the past or present")
    private LocalDate lastRechargeDate;

    @Column(name = "team_code", nullable = false)
    @NotNull(message = "{field.team-code.required}")
    private Integer teamCode;

    @Column(name = "next_inspection", nullable = false)
    @NotNull(message = "{field.next-inspection.required}")
    @Future(message = "Next Inspection must be in the future")
    private LocalDate nextInspection;

    @Column(name = "extinguisher_status", nullable = false)
    @NotNull(message = "{field.extinguisher-status.required}")
    private ExtinguisherStatus extinguisherStatus;

    @ManyToOne
    @JoinColumn(name = "id_localization")
    private Localization localization;

    @OneToMany(mappedBy = "extinguisher", fetch = FetchType.LAZY)
    private List<HistoricManutention> historicManutention;

    @OneToOne(mappedBy = "extinguisher")
    private QRCode qrCode;

    public Extinguisher(Extinguisher extinguisher) {
        this.id = extinguisher.getId();
        this.extinguisherType = extinguisher.getExtinguisherType();
        this.capacity = extinguisher.getCapacity();
        this.manufacturerCode = extinguisher.getManufacturerCode();
        this.expirationDate = extinguisher.getExpirationDate();
        this.lastRechargeDate = extinguisher.getLastRechargeDate();
        this.teamCode = extinguisher.getTeamCode();
        this.nextInspection = extinguisher.getNextInspection();
        this.extinguisherStatus = extinguisher.getExtinguisherStatus();
        this.localization = extinguisher.getLocalization();
        this.historicManutention = extinguisher.getHistoricManutention();
        this.qrCode = extinguisher.getQrCode();
    }

    public Extinguisher(String id,
                        String extinguisherType,
                        Integer capacity,
                        String manufacturerCode,
                        String expirationDate,
                        String lastRechargeDate,
                        Integer teamCode,
                        String nextInspection,
                        String extinguisherStatus)  {
        this.id = id;
        this.extinguisherType = ExtinguisherType.valueOf(extinguisherType);
        this.capacity = capacity;
        this.manufacturerCode = manufacturerCode;
        this.expirationDate = LocalDate.parse(expirationDate);
        this.lastRechargeDate = LocalDate.parse(lastRechargeDate);
        this.teamCode = teamCode;
        this.nextInspection = LocalDate.parse(nextInspection);
        this.extinguisherStatus = ExtinguisherStatus.valueOf(extinguisherStatus);
    }
}

    //observacoes

