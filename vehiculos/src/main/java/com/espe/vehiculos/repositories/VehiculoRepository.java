package com.espe.vehiculos.repositories;

import com.espe.vehiculos.models.entities.Vehiculo;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Long> {
    // Métodos adicionales si es necesario
}
