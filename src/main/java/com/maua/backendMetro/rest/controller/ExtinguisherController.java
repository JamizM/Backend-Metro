package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.repository.Extinguishers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/Extinguishers")
public class ExtinguisherController {

    private final Extinguishers extinguishers;

    public ExtinguisherController(Extinguishers extinguishers){
        this.extinguishers = extinguishers;
    }

    @GetMapping("/api/Extinguishers")
    public List<Extinguisher> getExtinguishers(){
        return extinguishers.findAll();
    }





}
