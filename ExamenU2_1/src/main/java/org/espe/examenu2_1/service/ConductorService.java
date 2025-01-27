package org.espe.examenu2_1.service;

import org.espe.examenu2_1.model.Conductor;

import java.util.List;
import java.util.Optional;

public interface ConductorService {
    Conductor save(Conductor conductor);

    Optional<Conductor> findById(Long id);

    List<Conductor> findAll();

    void deleteById(Long id);
}
