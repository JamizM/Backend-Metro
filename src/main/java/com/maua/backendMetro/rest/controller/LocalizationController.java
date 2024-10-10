package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.domain.repository.Extinguishers;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Localizations")
public class LocalizationController {

    private final Localizations localizations;

    public LocalizationController(Localizations localizations){
        this.localizations = localizations;
    }

    @GetMapping("/api/Localizations")
    public Iterable<Localization> getLocalizations(){
        return localizations.findAll();
    }

    @PostMapping
    public ResponseEntity<Localization> createLocalization(@RequestBody @Valid Localization localization){
        Localization savedLocalization = localizations.save(localization);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(savedLocalization);
    }
}
