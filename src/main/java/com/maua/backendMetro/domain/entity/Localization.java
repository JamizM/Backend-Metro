package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="area")
    @Enumerated(EnumType.STRING)
    @NotNull
    private MetroLine area;

    @Column(name="subway_station")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SubwayStation subwayStation;

    @Column(name="detailed_location")
    @NotNull
    private String detailedLocation;

    @OneToMany(mappedBy = "localization")
    private List<Extinguisher> extinguisherList;

    //local detalhado
    //observacoes
}
