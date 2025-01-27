package org.espe.examenu2_1.controller;

import org.espe.examenu2_1.model.Conductor;
import org.espe.examenu2_1.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    @Autowired
    private ConductorService service;

    // Crear un conductor
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Conductor conductor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conductor));
    }

    // Listar todos los conductores
    @GetMapping
    public ResponseEntity<List<Conductor>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    // Obtener un conductor por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id) {
        Optional<Conductor> conductor = service.findById(id);
        if (conductor.isPresent()) {
            return ResponseEntity.ok(conductor.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }
    }

    // Actualizar un conductor
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Conductor conductor, @PathVariable Long id) {
        Optional<Conductor> optionalConductor = service.findById(id);
        if (optionalConductor.isPresent()) {
            Conductor conductorActualizado = optionalConductor.get();
            conductorActualizado.setNombre(conductor.getNombre());
            conductorActualizado.setLicencia(conductor.getLicencia());
            conductorActualizado.setTelefono(conductor.getTelefono());
            conductorActualizado.setDireccion(conductor.getDireccion());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conductorActualizado));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }
    }

    // Eliminar un conductor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Conductor> conductor = service.findById(id);
        if (conductor.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }
    }
}
