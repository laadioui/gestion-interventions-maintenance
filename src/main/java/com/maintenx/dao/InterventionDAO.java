package com.maintenx.dao;
import com.maintenx.model.Intervention;
import com.maintenx.service.InterventionSearchCriteria;
import java.util.List;
public interface InterventionDAO extends GenericDAO<Intervention, Long> {
    List<Intervention> search(InterventionSearchCriteria criteria);
    String nextReference();
}
