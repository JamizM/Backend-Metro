package com.maua.backendMetro.domain.repository;

import com.maua.backendMetro.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "Users")
public interface Users extends JpaRepository<User, Integer> {
}
