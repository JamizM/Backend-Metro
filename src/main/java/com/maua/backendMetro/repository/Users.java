package com.maua.backendMetro.repository;

import com.maua.backendMetro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.PathVariable;

@RepositoryRestResource(path = "Users")
public interface Users extends JpaRepository<User, Integer> {
}
