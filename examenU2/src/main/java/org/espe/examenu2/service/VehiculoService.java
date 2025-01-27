package org.espe.examenu2.service;


import org.espe.examenu2.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {
    Vehiculo save(Vehiculo vehiculo);

    Optional<Vehiculo> findById(Long id);

    List<Vehiculo> findAll();

    void deleteById(Long id);
}
