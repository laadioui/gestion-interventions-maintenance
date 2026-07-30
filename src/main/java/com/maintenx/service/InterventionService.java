package com.maintenx.service;
import com.maintenx.model.*;
import com.maintenx.model.enums.*;
import java.io.File;
import java.util.List;
public interface InterventionService {
    List<Intervention> findAuthorized(Utilisateur user);
    List<Intervention> search(InterventionSearchCriteria criteria, Utilisateur user);
    Intervention create(Intervention intervention, Utilisateur actor);
    void update(Intervention intervention, Utilisateur actor);
    void assign(long interventionId, long technicienId, Utilisateur actor);
    void changeStatus(long interventionId, StatutIntervention status, String solution, Utilisateur actor);
    void cancel(long interventionId, Utilisateur actor);
    File exportCsv(List<Intervention> interventions, File file);
}
