package com.espe.conductores.repositories;

import com.espe.conductores.models.entities.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {
}
