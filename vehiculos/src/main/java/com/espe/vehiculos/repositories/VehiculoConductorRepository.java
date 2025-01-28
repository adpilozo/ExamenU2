package com.espe.vehiculos.repositories;

import com.espe.vehiculos.models.entities.VehiculoConductor;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoConductorRepository extends CrudRepository<VehiculoConductor, Long> {
    // Métodos adicionales si es necesario
}
