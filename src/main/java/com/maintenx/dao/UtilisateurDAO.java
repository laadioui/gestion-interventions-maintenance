package com.maintenx.dao;
import com.maintenx.model.Utilisateur;
import java.util.Optional;
public interface UtilisateurDAO extends GenericDAO<Utilisateur, Long> {
    Optional<Utilisateur> findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
