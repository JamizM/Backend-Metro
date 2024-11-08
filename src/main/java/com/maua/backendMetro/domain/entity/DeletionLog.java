package com.maua.backendMetro.domain.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@Entity
@Data
public class DeletionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "id_extinguisher")
    private String extinguisher;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "deletion_reason")
    private String deletionReason;

    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

    public DeletionLog(String extinguisher, String userName, String deletionReason, LocalDateTime deletionDate) {
        this.extinguisher = extinguisher;
        this.userName = userName;
        this.deletionReason = deletionReason;
        this.deletionDate = deletionDate;
    }

    public DeletionLog(String userName, String deletionReason, LocalDateTime now) {
    }
}
