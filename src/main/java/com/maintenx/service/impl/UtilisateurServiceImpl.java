package com.maintenx.service.impl;
import com.maintenx.exception.*;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import com.maintenx.service.*;
import com.maintenx.util.PasswordHasher;
import com.maintenx.validation.InputValidator;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
public class UtilisateurServiceImpl implements UtilisateurService {
    private final InMemoryStore store; private final JournalActiviteService journal;
    public UtilisateurServiceImpl(InMemoryStore store, JournalActiviteService journal) { this.store = store; this.journal = journal; }
    public List<Utilisateur> findAll() { return store.utilisateurs.stream().sorted(Comparator.comparing(Utilisateur::getId)).toList(); }
    public List<Utilisateur> search(String text, Role role, Boolean actif) {
        String q = text == null ? "" : text.toLowerCase();
        return findAll().stream()
                .filter(u -> q.isBlank() || (u.getNom()+" "+u.getPrenom()+" "+u.getEmail()+" "+u.getNomUtilisateur()).toLowerCase().contains(q))
                .filter(u -> role == null || u.getRole() == role)
                .filter(u -> actif == null || u.isActif() == actif)
                .toList();
    }
    public Utilisateur create(Utilisateur u, String rawPassword) {
        validate(u); InputValidator.password(rawPassword);
        if (store.utilisateurs.stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(u.getEmail()))) throw new DuplicateResourceException("Email déjà utilisé.");
        if (store.utilisateurs.stream().anyMatch(x -> x.getNomUtilisateur().equalsIgnoreCase(u.getNomUtilisateur()))) throw new DuplicateResourceException("Nom d'utilisateur déjà utilisé.");
        u.setId(store.userSeq.getAndIncrement()); u.setMotDePasseHash(PasswordHasher.hash(rawPassword)); u.setDateCreation(LocalDateTime.now()); u.setDateModification(LocalDateTime.now());
        store.utilisateurs.add(u); journal.log("system", "UTILISATEUR_CREATE", u.getNomUtilisateur()); return u;
    }
    public void update(Utilisateur u) {
        validate(u); var existing = byId(u.getId());
        existing.setNom(u.getNom()); existing.setPrenom(u.getPrenom()); existing.setEmail(u.getEmail()); existing.setNomUtilisateur(u.getNomUtilisateur());
        existing.setRole(u.getRole()); existing.setTelephone(u.getTelephone()); existing.setActif(u.isActif()); existing.setDateModification(LocalDateTime.now());
        journal.log("system", "UTILISATEUR_UPDATE", existing.getNomUtilisateur());
    }
    public void deactivate(long id, long connectedId) { if (id == connectedId) throw new BusinessException("Vous ne pouvez pas désactiver votre propre compte."); byId(id).setActif(false); }
    public void activate(long id) { byId(id).setActif(true); }
    public void resetPassword(long id, String newPassword) { InputValidator.password(newPassword); byId(id).setMotDePasseHash(PasswordHasher.hash(newPassword)); }
    private Utilisateur byId(long id) { return store.utilisateurs.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable.")); }
    private void validate(Utilisateur u) {
        InputValidator.required(u.getNom(), "nom"); InputValidator.required(u.getPrenom(), "prénom"); InputValidator.email(u.getEmail());
        InputValidator.required(u.getNomUtilisateur(), "nom d'utilisateur"); InputValidator.phone(u.getTelephone());
        if (u.getRole() == null) throw new ValidationException("Le rôle est obligatoire.");
    }
}
