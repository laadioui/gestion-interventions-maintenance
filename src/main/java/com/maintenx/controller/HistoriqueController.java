package com.maintenx.controller;
import com.maintenx.model.HistoriqueIntervention;
import com.maintenx.service.HistoriqueService;
import java.util.List;
public class HistoriqueController {
    private final HistoriqueService service;
    public HistoriqueController(HistoriqueService service) { this.service = service; }
    public List<HistoriqueIntervention> all() { return service.findAll(); }
    public List<HistoriqueIntervention> byIntervention(long id) { return service.findByIntervention(id); }
}
