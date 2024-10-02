package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Localization;
import com.maua.backendMetro.domain.repository.Localizations;
import com.maua.backendMetro.domain.repository.Extinguishers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
