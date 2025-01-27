package org.espe.examenu2.controller;

import org.espe.examenu2.model.Vehiculo;
import org.espe.examenu2.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService service;

    // Crear un vehículo
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoGuardado = service.save(vehiculo);
        return ResponseEntity.created(URI.create("/api/vehiculos/" + vehiculoGuardado.getId()))
                .body(vehiculoGuardado);
    }

    // Listar todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener un vehículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        return verificarExistenciaDeVehiculo(id);
    }

    // Actualizar un vehículo
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Vehiculo vehiculo, @PathVariable Long id) {
        ResponseEntity<?> response = verificarExistenciaDeVehiculo(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return response;
        }

        Optional<Vehiculo> optionalVehiculo = service.findById(id);
        Vehiculo vehiculoActualizado = optionalVehiculo.get();

        // Actualizar dinámicamente solo los campos proporcionados
        if (vehiculo.getMarca() != null) vehiculoActualizado.setMarca(vehiculo.getMarca());
        if (vehiculo.getModelo() != null) vehiculoActualizado.setModelo(vehiculo.getModelo());
        if (vehiculo.getYear() != null) vehiculoActualizado.setYear(vehiculo.getYear());
        if (vehiculo.getConductores() != null) vehiculoActualizado.setConductores(vehiculo.getConductores());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(vehiculoActualizado));
    }

    // Eliminar un vehículo por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        ResponseEntity<?> response = verificarExistenciaDeVehiculo(id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return response;
        }

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Método común para verificar existencia de un vehículo por ID
    private ResponseEntity<?> verificarExistenciaDeVehiculo(Long id) {
        Optional<Vehiculo> vehiculo = service.findById(id);
        if (vehiculo.isPresent()) {
            return ResponseEntity.ok(vehiculo.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
        }
    }
}
