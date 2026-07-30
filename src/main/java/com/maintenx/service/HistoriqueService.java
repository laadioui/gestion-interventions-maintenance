package com.maintenx.service;
import com.maintenx.model.HistoriqueIntervention;
import java.util.List;
public interface HistoriqueService {
    void add(long interventionId, String action, String oldValue, String newValue, String user);
    List<HistoriqueIntervention> findByIntervention(long interventionId);
    List<HistoriqueIntervention> findAll();
}
