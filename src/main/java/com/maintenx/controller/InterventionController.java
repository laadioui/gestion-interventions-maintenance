package com.maintenx.controller;
import com.maintenx.model.*;
import com.maintenx.model.enums.StatutIntervention;
import com.maintenx.service.*;
import java.io.File;
import java.util.List;
public class InterventionController {
    private final InterventionService service; private final Utilisateur user;
    public InterventionController(InterventionService service, Utilisateur user) { this.service = service; this.user = user; }
    public List<Intervention> all() { return service.findAuthorized(user); }
    public List<Intervention> search(InterventionSearchCriteria c) { return service.search(c, user); }
    public Intervention create(Intervention i) { return service.create(i, user); }
    public void update(Intervention i) { service.update(i, user); }
    public void assign(long id, long techId) { service.assign(id, techId, user); }
    public void status(long id, StatutIntervention status, String solution) { service.changeStatus(id, status, solution, user); }
    public File export(List<Intervention> list, File file) { return service.exportCsv(list, file); }
}
