package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.Service.ExtinguisherService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.enums.MetroLine;
import com.maua.backendMetro.domain.entity.enums.SubwayStation;
import com.maua.backendMetro.domain.repository.Extinguishers;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.ExtinguisherDTO;
import com.maua.backendMetro.util.MessageWriterEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Extinguishers")
@AllArgsConstructor
@Validated
public class ExtinguisherController {

    private Extinguishers extinguishers;
    private ExtinguisherService extinguisherService;

    @GetMapping
    public List<Extinguisher> listAll() {
        return extinguishers.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Extinguisher> findById(@PathVariable String id) {
        Extinguisher extinguisher = extinguishers.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Extinguisher not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(extinguisher);
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

    //crud padrao

    //regras de negocio estara abaixo
    @GetMapping("/Search-Extinguisher-By-Localization")
    public Optional<Extinguisher> getExtinguisherByLocalizationDetails(
            @RequestParam(required = false) @NotNull(message = "{field.area.required-on-extinguisher-controller}")
            MetroLine area,
            @RequestParam(required = false) @NotNull(message = "{field.subway-station.required-on-extinguisher-controller}")
            SubwayStation subwayStation,
            @RequestParam(required = false) @NotNull(message = "{field.detailed-location.required-on-extinguisher-controller}")
            String detailedLocation) {

        return extinguisherService.findExtinguisherByLocalizationDetails(area, subwayStation, detailedLocation);
    }

    @GetMapping("/Search-Extinguisher-By-Expiration-Date")
    public List<String> alertExpirationDateOfExtinguisher() throws MessageWriterEntity {
        return extinguisherService.verifyExpirationDateExtinguisherAndAlert();
    }
}
