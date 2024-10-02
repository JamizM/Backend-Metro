package com.maua.backendMetro.domain.entity;

import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Component;

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
    @NotNull
    private Integer area;

    @Column(name="subway_station")
    @Enumerated(EnumType.STRING)
    @NotNull
    private SubwayStation subwayStation;

    //local detalhado
    //observacoes
}
