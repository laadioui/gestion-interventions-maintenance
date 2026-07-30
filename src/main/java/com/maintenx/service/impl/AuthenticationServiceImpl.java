package com.maintenx.service.impl;
import com.maintenx.exception.AuthenticationException;
import com.maintenx.model.Utilisateur;
import com.maintenx.service.*;
import com.maintenx.util.PasswordHasher;
public class AuthenticationServiceImpl implements AuthenticationService {
    private final InMemoryStore store; private final JournalActiviteService journal;
    public AuthenticationServiceImpl(InMemoryStore store, JournalActiviteService journal) { this.store = store; this.journal = journal; }
    public Utilisateur login(String username, String password) {
        return store.utilisateurs.stream()
                .filter(u -> u.getNomUtilisateur().equalsIgnoreCase(username))
                .filter(Utilisateur::isActif)
                .filter(u -> PasswordHasher.verify(password, u.getMotDePasseHash()))
                .findFirst()
                .map(u -> { journal.log(u.getNomUtilisateur(), "CONNEXION", "Connexion réussie"); return u; })
                .orElseThrow(() -> new AuthenticationException("Identifiants invalides ou compte inactif."));
    }
}
