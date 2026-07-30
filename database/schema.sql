CREATE DATABASE IF NOT EXISTS gestion_maintenance CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE gestion_maintenance;

CREATE TABLE utilisateur (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nom VARCHAR(80) NOT NULL,
  prenom VARCHAR(80) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  nom_utilisateur VARCHAR(80) NOT NULL UNIQUE,
  mot_de_passe_hash VARCHAR(255) NOT NULL,
  role ENUM('ADMINISTRATEUR','RESPONSABLE','TECHNICIEN') NOT NULL,
  telephone VARCHAR(30),
  actif BOOLEAN NOT NULL DEFAULT TRUE,
  date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modification DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX idx_utilisateur_email(email),
  INDEX idx_utilisateur_login(nom_utilisateur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE technicien (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  matricule VARCHAR(40) NOT NULL UNIQUE,
  nom VARCHAR(80) NOT NULL,
  prenom VARCHAR(80) NOT NULL,
  email VARCHAR(160) NOT NULL UNIQUE,
  telephone VARCHAR(30),
  specialite ENUM('INFORMATIQUE','RESEAU','ELECTRICITE','MECANIQUE','CLIMATISATION','PLOMBERIE','MAINTENANCE_GENERALE','AUTRE') NOT NULL,
  disponible BOOLEAN NOT NULL DEFAULT TRUE,
  actif BOOLEAN NOT NULL DEFAULT TRUE,
  interventions_en_cours INT NOT NULL DEFAULT 0,
  date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_technicien_specialite(specialite),
  INDEX idx_technicien_disponible(disponible)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE intervention (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  reference VARCHAR(30) NOT NULL UNIQUE,
  titre VARCHAR(180) NOT NULL,
  description TEXT NOT NULL,
  categorie VARCHAR(80) NOT NULL,
  localisation VARCHAR(160) NOT NULL,
  equipement VARCHAR(160),
  date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  date_modification DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  date_souhaitee DATE,
  date_debut DATETIME,
  date_fin DATETIME,
  priorite ENUM('BASSE','MOYENNE','HAUTE','URGENTE') NOT NULL DEFAULT 'MOYENNE',
  statut ENUM('OUVERTE','AFFECTEE','EN_COURS','EN_ATTENTE','TERMINEE','ANNULEE') NOT NULL DEFAULT 'OUVERTE',
  demandeur_id BIGINT,
  technicien_id BIGINT,
  commentaire TEXT,
  diagnostic TEXT,
  solution_appliquee TEXT,
  cout_estime DECIMAL(12,2) NOT NULL DEFAULT 0,
  cout_reel DECIMAL(12,2) NOT NULL DEFAULT 0,
  CONSTRAINT fk_intervention_demandeur FOREIGN KEY (demandeur_id) REFERENCES utilisateur(id),
  CONSTRAINT fk_intervention_technicien FOREIGN KEY (technicien_id) REFERENCES technicien(id),
  CONSTRAINT chk_cout_reel CHECK (cout_reel >= 0),
  INDEX idx_intervention_reference(reference),
  INDEX idx_intervention_statut(statut),
  INDEX idx_intervention_priorite(priorite),
  INDEX idx_intervention_date_creation(date_creation),
  INDEX idx_intervention_technicien(technicien_id),
  INDEX idx_intervention_demandeur(demandeur_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE historique_intervention (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  intervention_id BIGINT NOT NULL,
  action VARCHAR(80) NOT NULL,
  ancienne_valeur TEXT,
  nouvelle_valeur TEXT,
  utilisateur VARCHAR(120) NOT NULL,
  date_action DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_hist_intervention FOREIGN KEY (intervention_id) REFERENCES intervention(id),
  INDEX idx_hist_intervention(intervention_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE journal_activite (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  utilisateur VARCHAR(120) NOT NULL,
  action VARCHAR(100) NOT NULL,
  details TEXT,
  date_action DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX idx_journal_date(date_action)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE parametre_application (
  cle VARCHAR(120) PRIMARY KEY,
  valeur TEXT NOT NULL,
  date_modification DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
