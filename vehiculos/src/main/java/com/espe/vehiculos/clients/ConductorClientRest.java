package com.espe.vehiculos.clients;

import com.espe.vehiculos.models.Conductor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "conductores", url = "http://localhost:8003/api/conductores")
public interface ConductorClientRest {

    @GetMapping("/{id}")
    Conductor findById(@PathVariable Long id);
}
