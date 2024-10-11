package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.exception.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Localizations")
public class LocalizationController {

    private final Localizations localizations;

    public LocalizationController(Localizations localizations){
        this.localizations = localizations;
    }

    @GetMapping
    public List<Localization> listAll() {
        return localizations.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Localization> findById(@PathVariable Long id) {
        Localization localization = localizations.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Extinguisher not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(localization);
    }

    @PostMapping
    public ResponseEntity<Localization> createLocalization(@RequestBody @Valid Localization localization){
        Localization savedLocalization = localizations.save(localization);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(savedLocalization);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocalization(@PathVariable Long id){
        localizations.findById(id)
                .map(localization -> {localizations.delete(localization);
                    return localization;
                })
                .orElseThrow(() -> new EntityNotFoundException("Localization not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Localization> updateLocalization(@PathVariable Long id, @RequestBody @Valid Localization localization){
        return localizations.findById(id)
                .map(localizationExist -> {
                    localization.setId(localizationExist.getId());
                    localizations.save(localization);
                    return ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_TYPE, "application/json")
                            .body(localization);
                }).orElseThrow(() -> new EntityNotFoundException("Localization not found"));
    }
}
