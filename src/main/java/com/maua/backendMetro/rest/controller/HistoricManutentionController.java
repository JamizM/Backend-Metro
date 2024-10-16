package com.maua.backendMetro.rest.controller;

import com.maua.backendMetro.Service.HistoricManutentionService;
import com.maua.backendMetro.domain.entity.Extinguisher;
import com.maua.backendMetro.domain.entity.HistoricManutention;
import com.maua.backendMetro.domain.repository.HistoricManutentions;
import com.maua.backendMetro.exception.EntityNotFoundException;
import com.maua.backendMetro.rest.controller.dto.HistoricManutentionDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/HistoricManutention")
@AllArgsConstructor
public class HistoricManutentionController {

    private HistoricManutentions historicManutentions;
    private HistoricManutentionService historicManutentionService;

    @GetMapping
    public List<HistoricManutention> listAll() {
        return historicManutentions.findAll();
    }

    @GetMapping("/{id}")
    public HistoricManutention findByID(@PathVariable @Valid Long id) {
        return historicManutentions
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("HistoricManutention not found"));
    }

    @PostMapping
    public HistoricManutention createHistoricManutention(@RequestBody @Valid HistoricManutentionDTO dto) {
        HistoricManutention savedHistoricManutention = historicManutentionService.createHistoricManutention(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(savedHistoricManutention).getBody();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteHistoricManutentions(@PathVariable Long id){
        historicManutentions.findById(id)
                .map(historicManutention -> {
                    historicManutentions.delete(historicManutention);
                    return historicManutention;
                })
                .orElseThrow(() -> new EntityNotFoundException("HistoricManutention not found"));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateHistoricManutentions(@PathVariable Long id, @RequestBody HistoricManutention historicManutention){
        historicManutentions.findById(id)
                .map(historicManutention1 -> {
                    historicManutention1.setIdManutention(historicManutention1.getIdManutention());
                    historicManutentions.save(historicManutention1);
                    return historicManutention;
                })
                .orElseThrow(() -> new EntityNotFoundException("HistoricManutention not found"));
    }

    @GetMapping("/Find-Historic-Manutention-By-ExtinguisherId") //funcao dando erro de parse String para Long
    public List<HistoricManutention> getHistoricManutentionByExtinguisherId(@RequestParam Extinguisher extinguisher) {
        return historicManutentionService.findHistoricManutentionByExtinguisherId(extinguisher);
    }
}