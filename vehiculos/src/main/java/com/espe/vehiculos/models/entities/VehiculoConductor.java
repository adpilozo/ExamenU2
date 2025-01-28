package com.espe.vehiculos.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos_conductores")
public class VehiculoConductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conductor_id", nullable = false)
    private Long conductorId;

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConductorId() {
        return conductorId;
    }

    public void setConductorId(Long conductorId) {
        this.conductorId = conductorId;
    }
}
