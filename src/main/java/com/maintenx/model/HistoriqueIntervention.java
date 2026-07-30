package com.maintenx.model;

import java.time.LocalDateTime;

public class HistoriqueIntervention {
    private long id;
    private long interventionId;
    private String action;
    private String ancienneValeur;
    private String nouvelleValeur;
    private String utilisateur;
    private LocalDateTime dateAction = LocalDateTime.now();
    public HistoriqueIntervention() {}
    public HistoriqueIntervention(long id, long interventionId, String action, String ancienneValeur, String nouvelleValeur, String utilisateur) {
        this.id = id; this.interventionId = interventionId; this.action = action; this.ancienneValeur = ancienneValeur; this.nouvelleValeur = nouvelleValeur; this.utilisateur = utilisateur;
    }
    public long getId() { return id; }
    public long getInterventionId() { return interventionId; }
    public String getAction() { return action; }
    public String getAncienneValeur() { return ancienneValeur; }
    public String getNouvelleValeur() { return nouvelleValeur; }
    public String getUtilisateur() { return utilisateur; }
    public LocalDateTime getDateAction() { return dateAction; }
}
