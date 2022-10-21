package com.example.backend.repository;

import com.example.backend.model.Prole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Prole, Long> {
    Prole findByName(String name);
}
