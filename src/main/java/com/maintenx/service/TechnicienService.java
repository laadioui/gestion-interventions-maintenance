package com.maintenx.service;
import com.maintenx.model.Technicien;
import com.maintenx.model.enums.Specialite;
import java.util.List;
public interface TechnicienService {
    List<Technicien> findAll();
    List<Technicien> search(String text, Specialite specialite, Boolean disponible);
    Technicien create(Technicien technicien);
    void update(Technicien technicien);
    void deactivate(long id);
    Technicien requireAssignable(long id);
    void refreshWorkload();
}
