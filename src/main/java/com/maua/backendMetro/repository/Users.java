package com.maua.backendMetro.repository;

import com.maua.backendMetro.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Users extends JpaRepository<User, Integer> {
}
