package com.maintenx.model;

import com.maintenx.model.enums.Specialite;
import java.time.LocalDateTime;

public class Technicien {
    private long id;
    private String matricule;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Specialite specialite;
    private boolean disponible = true;
    private boolean actif = true;
    private int interventionsEnCours;
    private LocalDateTime dateCreation = LocalDateTime.now();

    public Technicien() {}
    public Technicien(long id, String matricule, String nom, String prenom, String email, String telephone, Specialite specialite, boolean disponible, boolean actif) {
        this.id = id; this.matricule = matricule; this.nom = nom; this.prenom = prenom; this.email = email;
        this.telephone = telephone; this.specialite = specialite; this.disponible = disponible; this.actif = actif;
    }
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Specialite getSpecialite() { return specialite; }
    public void setSpecialite(Specialite specialite) { this.specialite = specialite; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public boolean isActif() { return actif; }
    public void setActif(boolean actif) { this.actif = actif; }
    public int getInterventionsEnCours() { return interventionsEnCours; }
    public void setInterventionsEnCours(int interventionsEnCours) { this.interventionsEnCours = interventionsEnCours; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public String nomComplet() { return prenom + " " + nom; }
    @Override public String toString() { return matricule + " - " + nomComplet(); }
}
