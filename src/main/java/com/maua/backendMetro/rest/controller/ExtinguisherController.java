package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/Extinguishers")
@AllArgsConstructor
public class ExtinguisherController {

    private Extinguishers extinguishers;

    @GetMapping
    public List<Extinguisher> listAll() {
        return extinguishers.findAll();
    }

    @GetMapping("/{id}")
    public Extinguisher findById(@PathVariable String id) {
        return extinguishers
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Extinguisher not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Extinguisher> createExtinguisher(@RequestBody @Valid Extinguisher extinguisher){
        Extinguisher savedExtinguisher = extinguishers.save(extinguisher);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(savedExtinguisher);
    }

    @DeleteMapping("/{id}")
    public void deleteExtinguisher(@PathVariable String id){
        extinguishers.findById(id)
                .map(extinguisher -> {extinguishers.delete(extinguisher);
                    return extinguisher;
                })
                .orElseThrow(() -> new EntityNotFoundException("Extinguisher not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateExtinguisher(@PathVariable String id, @RequestBody Extinguisher extinguisher){
        extinguishers.findById(id)
                .map(extinguisher1 -> {
                    extinguisher.setId(extinguisher1.getId());
                    extinguishers.save(extinguisher);
                    return extinguisher;
                })
                .orElseThrow(() -> new EntityNotFoundException("Extinguisher not found"));
    }
}
