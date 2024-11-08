package com.maua.backendMetro.domain.repository;

import com.maua.backendMetro.domain.entity.DeletionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeletionLogRepository extends JpaRepository<DeletionLog, Long> {
}
