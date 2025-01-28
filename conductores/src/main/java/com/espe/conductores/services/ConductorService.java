package com.espe.conductores.services;

import com.espe.conductores.models.entities.Conductor;

import java.util.List;
import java.util.Optional;

public interface ConductorService {

    List<Conductor> findAll();

    Optional<Conductor> findById(Long id);

    Conductor save(Conductor conductor);

    void deleteById(Long id);
}
