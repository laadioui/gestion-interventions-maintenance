USE gestion_maintenance;
CREATE OR REPLACE VIEW v_interventions_detaillees AS
SELECT i.reference, i.titre, i.categorie, i.priorite, i.statut, i.localisation,
       CONCAT(u.prenom,' ',u.nom) AS demandeur,
       CONCAT(t.prenom,' ',t.nom) AS technicien
FROM intervention i
LEFT JOIN utilisateur u ON u.id = i.demandeur_id
LEFT JOIN technicien t ON t.id = i.technicien_id;
CREATE OR REPLACE VIEW v_dashboard_statut AS SELECT statut, COUNT(*) total FROM intervention GROUP BY statut;
