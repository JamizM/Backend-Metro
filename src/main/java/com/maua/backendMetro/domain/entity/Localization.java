package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="localization")
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer LocalizationId;

    @Column(name="area")
    @NotNull
    private Integer area;

    @Column(name="subway_station")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SubwayStation subwayStation;

    //local detalhado
    //observacoes
}
