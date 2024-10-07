package com.maua.backendMetro.Service;

import com.maua.backendMetro.domain.entity.Extinguisher;

import java.util.List;

public interface ExtinguisherService {

    public  List<Extinguisher> expirationAlert(Extinguisher extinguisher);

    public List<Extinguisher> nextInspectionAlert(Extinguisher extinguisher);
}
