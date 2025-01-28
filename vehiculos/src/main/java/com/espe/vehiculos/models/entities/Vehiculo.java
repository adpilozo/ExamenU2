package com.espe.vehiculos.models.entities;

import com.espe.vehiculos.models.Conductor;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String modelo;
    private String placa;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @OneToMany
    @JoinColumn(name = "vehiculo_id")
    private List<VehiculoConductor> vehiculoConductores;

    @Transient
    private List<Conductor> conductores;

    public Vehiculo() {
        this.vehiculoConductores = new ArrayList<>();
        this.conductores = new ArrayList<>();
        this.fechaCreacion = new Date();
    }

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<VehiculoConductor> getVehiculoConductores() {
        return vehiculoConductores;
    }

    public void setVehiculoConductores(List<VehiculoConductor> vehiculoConductores) {
        this.vehiculoConductores = vehiculoConductores;
    }

    public List<Conductor> getConductores() {
        return conductores;
    }

    public void setConductores(List<Conductor> conductores) {
        this.conductores = conductores;
    }
}
