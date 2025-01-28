package com.espe.conductores.controllers;

import com.espe.conductores.models.entities.Conductor;
import com.espe.conductores.services.ConductorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/conductores")
public class ConductorController {

    @Autowired
    private ConductorService service;

    @GetMapping
    public List<Conductor> listar() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<Conductor> conductor = service.findById(id);

        if (conductor.isPresent()) {
            return ResponseEntity.ok(conductor.get()); // Retorna el conductor encontrado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Conductor no encontrado")); // Retorna un mapa con el mensaje de error
        }
    }


    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Conductor conductor, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }

        // Asignar fecha de creación
        conductor.setFechaCreacion(new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(conductor));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor no encontrado");
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
