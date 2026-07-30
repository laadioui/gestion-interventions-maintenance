package com.maintenx.service;
import com.maintenx.model.enums.*;
import java.math.BigDecimal;
import java.time.LocalDate;
public class InterventionSearchCriteria {
    public String reference;
    public String titre;
    public String categorie;
    public Priorite priorite;
    public StatutIntervention statut;
    public Long demandeurId;
    public Long technicienId;
    public String specialite;
    public LocalDate debutPeriode;
    public LocalDate finPeriode;
    public String localisation;
    public String equipement;
    public BigDecimal coutMin;
    public BigDecimal coutMax;
}
