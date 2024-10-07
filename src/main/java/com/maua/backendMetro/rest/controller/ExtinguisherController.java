package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/Extinguishers")
@AllArgsConstructor
public class ExtinguisherController {

    private ExtinguisherService extinguisherService;

    @PostMapping
    public ResponseEntity<Extinguisher> createExtinguisher(@Valid @RequestBody ExtinguisherDTO extinguisherDTO) {
        Extinguisher extinguisher = extinguisherService.createExtinguisher(extinguisherDTO);
        return ResponseEntity.created(URI.create(
                String.format("/extinguishers/%s", extinguisher.getId())))
                .body(extinguisher);
    }
}
