package com.maintenx.controller;
import com.maintenx.model.Technicien;
import com.maintenx.model.enums.Specialite;
import com.maintenx.service.TechnicienService;
import java.util.List;
public class TechnicienController {
    private final TechnicienService service;
    public TechnicienController(TechnicienService service) { this.service = service; }
    public List<Technicien> search(String text, Specialite specialite, Boolean disponible) { return service.search(text, specialite, disponible); }
    public List<Technicien> all() { return service.findAll(); }
    public Technicien create(Technicien t) { return service.create(t); }
    public void update(Technicien t) { service.update(t); }
    public void deactivate(long id) { service.deactivate(id); }
}
