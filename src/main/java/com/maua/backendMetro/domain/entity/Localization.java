package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Entity
public class Localization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="area")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{field.area.required}")
    private MetroLine area;

    @Column(name="subway_station")
    @Enumerated(EnumType.STRING)
    private SubwayStation subwayStation;

    @Column(name="detailed_location")
    @NotNull(message = "{field.detailed-location.required}")
    private String detailedLocation;
    //observacoes
}
