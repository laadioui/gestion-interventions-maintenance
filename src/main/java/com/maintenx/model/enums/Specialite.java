package com.maintenx.model.enums;
public enum Specialite {
    INFORMATIQUE("Informatique"), RESEAU("Réseau"), ELECTRICITE("Électricité"), MECANIQUE("Mécanique"),
    CLIMATISATION("Climatisation"), PLOMBERIE("Plomberie"), MAINTENANCE_GENERALE("Maintenance générale"), AUTRE("Autre");
    private final String libelle;
    Specialite(String libelle) { this.libelle = libelle; }
    public String getLibelle() { return libelle; }
    @Override public String toString() { return libelle; }
}
