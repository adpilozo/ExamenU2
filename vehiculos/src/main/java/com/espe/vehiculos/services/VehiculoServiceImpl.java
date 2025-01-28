package com.espe.vehiculos.services;

import com.espe.vehiculos.clients.ConductorClientRest;
import com.espe.vehiculos.models.Conductor;
import com.espe.vehiculos.models.entities.Vehiculo;
import com.espe.vehiculos.models.entities.VehiculoConductor;
import com.espe.vehiculos.repositories.VehiculoRepository;
import com.espe.vehiculos.repositories.VehiculoConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private ConductorClientRest conductorClientRest;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private VehiculoConductorRepository vehiculoConductorRepository;

    @Override
    public List<Vehiculo> findAll() {
        List<Vehiculo> vehiculos = (List<Vehiculo>) vehiculoRepository.findAll();

        for (Vehiculo vehiculo : vehiculos) {
            List<Conductor> conductores = new ArrayList<>();
            for (VehiculoConductor vehiculoConductor : vehiculo.getVehiculoConductores()) {
                Conductor conductor = conductorClientRest.findById(vehiculoConductor.getConductorId());
                conductores.add(conductor);
            }
            vehiculo.setConductores(conductores);
        }
        return vehiculos;
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id);

        if (optionalVehiculo.isPresent()) {
            Vehiculo vehiculo = optionalVehiculo.get();
            List<Conductor> conductores = new ArrayList<>();
            for (VehiculoConductor vehiculoConductor : vehiculo.getVehiculoConductores()) {
                Conductor conductor = conductorClientRest.findById(vehiculoConductor.getConductorId());
                conductores.add(conductor);
            }
            vehiculo.setConductores(conductores);
        }
        return optionalVehiculo;
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        vehiculoRepository.deleteById(id);
    }

    @Override
    public Optional<Conductor> addConductor(Conductor conductor, Long id) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(id);
        if (optionalVehiculo.isPresent()) {
            Vehiculo vehiculo = optionalVehiculo.get();
            Conductor conductorTemp = conductorClientRest.findById(conductor.getId());

            // Crear la relación VehiculoConductor
            VehiculoConductor vehiculoConductor = new VehiculoConductor();
            vehiculoConductor.setConductorId(conductorTemp.getId());
            vehiculoConductorRepository.save(vehiculoConductor);

            // Añadir el conductor a la lista del vehículo
            vehiculo.getVehiculoConductores().add(vehiculoConductor);
            vehiculoRepository.save(vehiculo);

            return Optional.of(conductorTemp);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Conductor> removeConductor(Long conductorId, Long vehiculoId) {
        Optional<Vehiculo> optionalVehiculo = vehiculoRepository.findById(vehiculoId);
        if (optionalVehiculo.isPresent()) {
            Vehiculo vehiculo = optionalVehiculo.get();
            VehiculoConductor vehiculoConductor = vehiculo.getVehiculoConductores().stream()
                    .filter(vc -> vc.getConductorId().equals(conductorId))
                    .findFirst()
                    .orElse(null);

            if (vehiculoConductor != null) {
                vehiculo.getVehiculoConductores().remove(vehiculoConductor);
                vehiculoConductorRepository.delete(vehiculoConductor);
                vehiculoRepository.save(vehiculo);

                Conductor conductorTemp = conductorClientRest.findById(conductorId);
                return Optional.of(conductorTemp);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Vehiculo> findVehiculosByConductor(Long conductorId) {
        List<Vehiculo> vehiculos = (List<Vehiculo>) vehiculoRepository.findAll();
        List<Vehiculo> vehiculosConductor = new ArrayList<>();

        for (Vehiculo vehiculo : vehiculos) {
            for (VehiculoConductor vehiculoConductor : vehiculo.getVehiculoConductores()) {
                if (vehiculoConductor.getConductorId().equals(conductorId)) {
                    vehiculosConductor.add(vehiculo);
                }
            }
        }
        return vehiculosConductor;
    }

    @Override
    public List<Conductor> findConductoresByVehiculo(Long vehiculoId) {
        List<Conductor> conductores = new ArrayList<>();
        Vehiculo vehiculo = vehiculoRepository.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));

        for (VehiculoConductor vehiculoConductor : vehiculo.getVehiculoConductores()) {
            Conductor conductor = conductorClientRest.findById(vehiculoConductor.getConductorId());
            conductores.add(conductor);
        }

        return conductores;
    }
}
