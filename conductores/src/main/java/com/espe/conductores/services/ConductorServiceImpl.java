package com.espe.conductores.services;

import com.espe.conductores.models.entities.Conductor;
import com.espe.conductores.repositories.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository repository;

    @Override
    public List<Conductor> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Conductor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Conductor save(Conductor conductor) {
        return repository.save(conductor);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
