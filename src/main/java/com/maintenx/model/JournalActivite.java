package com.maintenx.model;

import java.time.LocalDateTime;

public class JournalActivite {
    private final long id;
    private final String utilisateur;
    private final String action;
    private final String details;
    private final LocalDateTime dateAction;
    public JournalActivite(long id, String utilisateur, String action, String details) {
        this.id = id; this.utilisateur = utilisateur; this.action = action; this.details = details; this.dateAction = LocalDateTime.now();
    }
    public long getId() { return id; }
    public String getUtilisateur() { return utilisateur; }
    public String getAction() { return action; }
    public String getDetails() { return details; }
    public LocalDateTime getDateAction() { return dateAction; }
}
