package com.espe.vehiculos.controllers;

import com.espe.vehiculos.models.Conductor;
import com.espe.vehiculos.models.entities.Vehiculo;
import com.espe.vehiculos.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService servicio;

    // Crear un nuevo vehículo
    @PostMapping
    public ResponseEntity<?> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoCreado = servicio.save(vehiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoCreado);
    }

    // Obtener todos los vehículos
    @GetMapping
    public ResponseEntity<List<Vehiculo>> obtenerTodosLosVehiculos() {
        List<Vehiculo> vehiculos = servicio.findAll();
        return ResponseEntity.ok(vehiculos);
    }

    // Asignar conductor a vehículo
    @PostMapping("/{id}/conductores")
    public ResponseEntity<?> agregarConductor(@RequestBody Conductor conductor, @PathVariable Long id) {
        Optional<Conductor> conductorAsignado = servicio.addConductor(conductor, id);
        if (conductorAsignado.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(conductorAsignado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehículo no encontrado");
    }

    // Remover conductor de vehículo
    @DeleteMapping("/{vehiculoId}/conductores/{conductorId}")
    public ResponseEntity<?> removerConductor(@PathVariable Long vehiculoId, @PathVariable Long conductorId) {
        Optional<Conductor> conductorRemovido = servicio.removeConductor(conductorId, vehiculoId);
        if (conductorRemovido.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(conductorRemovido.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conductor o Vehículo no encontrado");
    }

    // Listar conductores asignados a un vehículo
    @GetMapping("/{vehiculoId}/conductores")
    public ResponseEntity<List<Conductor>> conductoresPorVehiculo(@PathVariable Long vehiculoId) {
        List<Conductor> conductores = servicio.findConductoresByVehiculo(vehiculoId);
        return ResponseEntity.ok(conductores);
    }

    // Listar vehículos asignados a un conductor
    @GetMapping("/conductores/{conductorId}")
    public ResponseEntity<List<Vehiculo>> vehiculosPorConductor(@PathVariable Long conductorId) {
        List<Vehiculo> vehiculos = servicio.findVehiculosByConductor(conductorId);
        return ResponseEntity.ok(vehiculos);
    }
}
