package com.maintenx.model;

import com.maintenx.model.enums.Role;
import java.time.LocalDateTime;
import java.util.Objects;

public class Utilisateur {
    private long id;
    private String nom;
    private String prenom;
    private String email;
    private String nomUtilisateur;
    private String motDePasseHash;
    private Role role;
    private String telephone;
    private boolean actif = true;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateModification = LocalDateTime.now();

    public Utilisateur() {}
    public Utilisateur(long id, String nom, String prenom, String email, String nomUtilisateur, String motDePasseHash, Role role, String telephone, boolean actif) {
        this.id = id; this.nom = nom; this.prenom = prenom; this.email = email; this.nomUtilisateur = nomUtilisateur;
        this.motDePasseHash = motDePasseHash; this.role = role; this.telephone = telephone; this.actif = actif;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNomUtilisateur() { return nomUtilisateur; }
    public void setNomUtilisateur(String nomUtilisateur) { this.nomUtilisateur = nomUtilisateur; }
    public String getMotDePasseHash() { return motDePasseHash; }
    public void setMotDePasseHash(String motDePasseHash) { this.motDePasseHash = motDePasseHash; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public String nomComplet() { return prenom + " " + nom; }
    @Override public String toString() { return nomComplet() + " (" + role + ")"; }
    @Override public boolean equals(Object o) { return o instanceof Utilisateur that && id == that.id; }
    @Override public int hashCode() { return Objects.hash(id); }
}
