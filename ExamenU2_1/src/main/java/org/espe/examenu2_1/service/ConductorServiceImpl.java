package org.espe.examenu2_1.service;

import org.espe.examenu2_1.repositories.ConductorRepository;
import org.espe.examenu2_1.model.Conductor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConductorServiceImpl implements ConductorService {

    @Autowired
    private ConductorRepository repository;

    @Override
    public Conductor save(Conductor conductor) {
        return repository.save(conductor);
    }

    @Override
    public Optional<Conductor> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Conductor> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
