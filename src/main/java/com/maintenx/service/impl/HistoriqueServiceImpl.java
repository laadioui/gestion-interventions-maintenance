package com.maintenx.service.impl;
import com.maintenx.model.HistoriqueIntervention;
import com.maintenx.service.*;
import java.util.Comparator;
import java.util.List;
public class HistoriqueServiceImpl implements HistoriqueService {
    private final InMemoryStore store;
    public HistoriqueServiceImpl(InMemoryStore store) { this.store = store; }
    public void add(long interventionId, String action, String oldValue, String newValue, String user) { store.historiques.add(new HistoriqueIntervention(store.histSeq.getAndIncrement(), interventionId, action, oldValue, newValue, user)); }
    public List<HistoriqueIntervention> findByIntervention(long interventionId) { return store.historiques.stream().filter(h -> h.getInterventionId() == interventionId).toList(); }
    public List<HistoriqueIntervention> findAll() { return store.historiques.stream().sorted(Comparator.comparing(HistoriqueIntervention::getDateAction).reversed()).toList(); }
}
