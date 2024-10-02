package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="localization")
@Entity
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer LocalizationId;

    @Column(name="area")
    @Enumerated(EnumType.STRING)
    @NotNull
    private MetroLine area;

    @Column(name="subway_station")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SubwayStation subwayStation;

    //local detalhado
    //observacoes
}
