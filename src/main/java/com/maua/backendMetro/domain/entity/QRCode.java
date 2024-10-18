package com.maua.backendMetro.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QRCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String qrData;

    @OneToOne
    @JoinColumn(name = "id_extinguisher")
    private Extinguisher extinguisher;
}
