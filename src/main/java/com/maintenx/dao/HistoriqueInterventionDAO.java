package com.maintenx.dao;
import com.maintenx.model.HistoriqueIntervention;
import java.util.List;
public interface HistoriqueInterventionDAO extends GenericDAO<HistoriqueIntervention, Long> { List<HistoriqueIntervention> findByInterventionId(long id); }
