package com.maintenx.service.impl;
import com.maintenx.model.Intervention;
import com.maintenx.model.enums.Priorite;
import com.maintenx.service.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class DashboardServiceImpl implements DashboardService {
    private final InMemoryStore store;
    public DashboardServiceImpl(InMemoryStore store) { this.store = store; }
    public long countUrgent() { return store.interventions.stream().filter(i -> i.getPriorite() == Priorite.URGENTE).count(); }
    public long countTechniciensActifs() { return store.techniciens.stream().filter(t -> t.isActif()).count(); }
    public long countTechniciensDisponibles() { return store.techniciens.stream().filter(t -> t.isDisponible()).count(); }
    public Map<String, Long> interventionsByStatus() { return store.interventions.stream().collect(Collectors.groupingBy(i -> i.getStatut().name(), Collectors.counting())); }
    public Map<String, Long> interventionsByPriority() { return store.interventions.stream().collect(Collectors.groupingBy(i -> i.getPriorite().name(), Collectors.counting())); }
    public Map<String, Long> interventionsByTechnician() { return store.interventions.stream().filter(i -> i.getTechnicien() != null).collect(Collectors.groupingBy(i -> i.getTechnicien().nomComplet(), Collectors.counting())); }
    public Map<String, Long> interventionsByMonth() { return store.interventions.stream().collect(Collectors.groupingBy(i -> i.getDateCreation().format(DateTimeFormatter.ofPattern("yyyy-MM")), Collectors.counting())); }
    public List<Intervention> recent() { return store.interventions.stream().sorted(Comparator.comparing(Intervention::getDateCreation).reversed()).limit(5).toList(); }
    public List<Intervention> urgent() { return store.interventions.stream().filter(i -> i.getPriorite() == Priorite.URGENTE).limit(5).toList(); }
}
