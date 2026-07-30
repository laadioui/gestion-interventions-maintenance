USE gestion_maintenance;
-- Mots de passe de démonstration documentés : Admin123!, Resp123!, Tech123!
-- Les hashes BCrypt ci-dessous sont des exemples valides à remplacer si besoin par l'application.
INSERT INTO utilisateur(nom,prenom,email,nom_utilisateur,mot_de_passe_hash,role,telephone) VALUES
('LAADIOUI','Othmane','admin@maintenx.local','admin','$2a$12$qR0AXw4ClhquxHqAcb49k.4tD1gGDXgtxZ0wvuN0NA9QKL0vVnU1y','ADMINISTRATEUR','0600000001'),
('El Ghazi','Anass','responsable@maintenx.local','responsable','$2a$12$qR0AXw4ClhquxHqAcb49k.4tD1gGDXgtxZ0wvuN0NA9QKL0vVnU1y','RESPONSABLE','0600000002'),
('Technicien','Ali','tech@maintenx.local','tech','$2a$12$qR0AXw4ClhquxHqAcb49k.4tD1gGDXgtxZ0wvuN0NA9QKL0vVnU1y','TECHNICIEN','0600000003');
INSERT INTO technicien(matricule,nom,prenom,email,telephone,specialite) VALUES
('TEC-001','Benali','Youssef','y.benali@sirecom.local','0611111111','INFORMATIQUE'),
('TEC-002','Haddad','Sara','s.haddad@sirecom.local','0622222222','RESEAU'),
('TEC-003','Amrani','Karim','k.amrani@sirecom.local','0633333333','ELECTRICITE');
INSERT INTO intervention(reference,titre,description,categorie,localisation,equipement,priorite,statut,demandeur_id,technicien_id,cout_estime,cout_reel,solution_appliquee)
SELECT CONCAT('INT-2026-', LPAD(n,4,'0')), CONCAT('Intervention de démonstration ',n), 'Demande de maintenance issue du jeu de données.', ELT(1+(n MOD 5),'Informatique','Réseau','Électricité','Climatisation','Plomberie'), CONCAT('Site ',1+(n MOD 4)), CONCAT('Équipement ',n), ELT(1+(n MOD 4),'BASSE','MOYENNE','HAUTE','URGENTE'), ELT(1+(n MOD 6),'OUVERTE','AFFECTEE','EN_COURS','EN_ATTENTE','TERMINEE','ANNULEE'), 1, IF(n MOD 2=0,1,NULL), 200+n*20, IF(n MOD 5=0,220+n*20,0), IF(n MOD 5=0,'Solution appliquée','')
FROM (SELECT 1 n UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 UNION SELECT 7 UNION SELECT 8 UNION SELECT 9 UNION SELECT 10 UNION SELECT 11 UNION SELECT 12 UNION SELECT 13 UNION SELECT 14 UNION SELECT 15 UNION SELECT 16 UNION SELECT 17 UNION SELECT 18 UNION SELECT 19 UNION SELECT 20) x;
INSERT INTO historique_intervention(intervention_id,action,ancienne_valeur,nouvelle_valeur,utilisateur)
SELECT id,'CREATION','',statut,'admin' FROM intervention;
INSERT INTO journal_activite(utilisateur,action,details) VALUES ('system','IMPORT_DEMO','Données de démonstration chargées.');
