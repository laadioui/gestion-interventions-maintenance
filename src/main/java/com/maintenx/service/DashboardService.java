package com.maintenx.service;
import com.maintenx.model.Intervention;
import java.util.List;
import java.util.Map;
public interface DashboardService {
    long countUrgent();
    long countTechniciensActifs();
    long countTechniciensDisponibles();
    Map<String, Long> interventionsByStatus();
    Map<String, Long> interventionsByPriority();
    Map<String, Long> interventionsByTechnician();
    Map<String, Long> interventionsByMonth();
    List<Intervention> recent();
    List<Intervention> urgent();
}
