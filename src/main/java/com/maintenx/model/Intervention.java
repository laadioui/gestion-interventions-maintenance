package com.maintenx.model;

import com.maintenx.model.enums.Priorite;
import com.maintenx.model.enums.StatutIntervention;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Intervention {
    private long id;
    private String reference;
    private String titre;
    private String description;
    private String categorie;
    private String localisation;
    private String equipement;
    private LocalDateTime dateCreation = LocalDateTime.now();
    private LocalDateTime dateModification = LocalDateTime.now();
    private LocalDate dateSouhaitee;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Priorite priorite = Priorite.MOYENNE;
    private StatutIntervention statut = StatutIntervention.OUVERTE;
    private Utilisateur demandeur;
    private Technicien technicien;
    private String commentaire;
    private String diagnostic;
    private String solutionAppliquee;
    private BigDecimal coutEstime = BigDecimal.ZERO;
    private BigDecimal coutReel = BigDecimal.ZERO;

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }
    public String getLocalisation() { return localisation; }
    public void setLocalisation(String localisation) { this.localisation = localisation; }
    public String getEquipement() { return equipement; }
    public void setEquipement(String equipement) { this.equipement = equipement; }
    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }
    public LocalDateTime getDateModification() { return dateModification; }
    public void setDateModification(LocalDateTime dateModification) { this.dateModification = dateModification; }
    public LocalDate getDateSouhaitee() { return dateSouhaitee; }
    public void setDateSouhaitee(LocalDate dateSouhaitee) { this.dateSouhaitee = dateSouhaitee; }
    public LocalDateTime getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDateTime dateDebut) { this.dateDebut = dateDebut; }
    public LocalDateTime getDateFin() { return dateFin; }
    public void setDateFin(LocalDateTime dateFin) { this.dateFin = dateFin; }
    public Priorite getPriorite() { return priorite; }
    public void setPriorite(Priorite priorite) { this.priorite = priorite; }
    public StatutIntervention getStatut() { return statut; }
    public void setStatut(StatutIntervention statut) { this.statut = statut; }
    public Utilisateur getDemandeur() { return demandeur; }
    public void setDemandeur(Utilisateur demandeur) { this.demandeur = demandeur; }
    public Technicien getTechnicien() { return technicien; }
    public void setTechnicien(Technicien technicien) { this.technicien = technicien; }
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public String getDiagnostic() { return diagnostic; }
    public void setDiagnostic(String diagnostic) { this.diagnostic = diagnostic; }
    public String getSolutionAppliquee() { return solutionAppliquee; }
    public void setSolutionAppliquee(String solutionAppliquee) { this.solutionAppliquee = solutionAppliquee; }
    public BigDecimal getCoutEstime() { return coutEstime; }
    public void setCoutEstime(BigDecimal coutEstime) { this.coutEstime = coutEstime; }
    public BigDecimal getCoutReel() { return coutReel; }
    public void setCoutReel(BigDecimal coutReel) { this.coutReel = coutReel; }
}
