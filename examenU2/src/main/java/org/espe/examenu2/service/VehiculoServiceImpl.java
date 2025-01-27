package org.espe.examenu2.service;

import org.espe.examenu2.model.Vehiculo;
import org.espe.examenu2.model.ConductorDTO;
import org.espe.examenu2.repositories.VehiculoRepository;
import org.espe.examenu2.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    private final String conductorServiceUrl = "http://localhost:8003/api/conductores";

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        // Llamada al servicio de conductores para obtener un conductor por ID
        ConductorDTO conductor = restTemplate.getForObject(conductorServiceUrl + "/" + vehiculo.getConductorId(), ConductorDTO.class);
        if (conductor != null) {
            vehiculo.setConductor(conductor); // Asociamos el conductor (usando el DTO) al vehículo
        }

        return repository.save(vehiculo); // Guarda el vehículo con el conductor asociado
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Vehiculo> findAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}