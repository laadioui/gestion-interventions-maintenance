package com.maintenx.service;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import java.util.List;
public interface UtilisateurService {
    List<Utilisateur> findAll();
    List<Utilisateur> search(String text, Role role, Boolean actif);
    Utilisateur create(Utilisateur utilisateur, String rawPassword);
    void update(Utilisateur utilisateur);
    void deactivate(long id, long connectedId);
    void activate(long id);
    void resetPassword(long id, String newPassword);
}
