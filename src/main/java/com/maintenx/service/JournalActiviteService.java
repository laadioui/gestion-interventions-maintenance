package com.maintenx.service;
import com.maintenx.model.JournalActivite;
import java.util.List;
public interface JournalActiviteService {
    void log(String utilisateur, String action, String details);
    List<JournalActivite> findAll();
}
