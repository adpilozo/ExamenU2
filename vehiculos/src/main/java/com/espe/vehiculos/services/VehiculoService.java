package com.espe.vehiculos.services;

import com.espe.vehiculos.models.Conductor;
import com.espe.vehiculos.models.entities.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    List<Vehiculo> findAll();
    Optional<Vehiculo> findById(Long id);
    Vehiculo save(Vehiculo vehiculo);
    void deleteById(Long id);

    Optional<Conductor> addConductor(Conductor conductor, Long id);
    Optional<Conductor> removeConductor(Long conductorId, Long vehiculoId);
    List<Vehiculo> findVehiculosByConductor(Long conductorId);
    List<Conductor> findConductoresByVehiculo(Long vehiculoId);
}
