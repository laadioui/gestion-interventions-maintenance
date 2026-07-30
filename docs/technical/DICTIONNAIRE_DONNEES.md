# Dictionnaire de données

## utilisateur

| Champ | Type | Obligatoire | Clé | Défaut | Description |
|---|---|---|---|---|---|
| id | BIGINT | Oui | PK | auto | Identifiant |
| email | VARCHAR(160) | Oui | UNIQUE |  | Email |
| nom_utilisateur | VARCHAR(80) | Oui | UNIQUE |  | Login |
| role | ENUM | Oui |  |  | Rôle |
| actif | BOOLEAN | Oui |  | TRUE | Statut |

## technicien

| Champ | Type | Obligatoire | Clé | Défaut | Description |
|---|---|---|---|---|---|
| id | BIGINT | Oui | PK | auto | Identifiant |
| matricule | VARCHAR(40) | Oui | UNIQUE |  | Matricule |
| specialite | ENUM | Oui |  |  | Domaine |
| disponible | BOOLEAN | Oui |  | TRUE | Disponibilité |

## intervention

| Champ | Type | Obligatoire | Clé | Défaut | Description |
|---|---|---|---|---|---|
| id | BIGINT | Oui | PK | auto | Identifiant |
| reference | VARCHAR(30) | Oui | UNIQUE |  | Référence INT |
| statut | ENUM | Oui | INDEX | OUVERTE | Cycle de vie |
| priorite | ENUM | Oui | INDEX | MOYENNE | Priorité |
| demandeur_id | BIGINT | Non | FK |  | Utilisateur |
| technicien_id | BIGINT | Non | FK |  | Technicien |

## historique_intervention

| Champ | Type | Obligatoire | Clé | Défaut | Description |
|---|---|---|---|---|---|
| id | BIGINT | Oui | PK | auto | Identifiant |
| intervention_id | BIGINT | Oui | FK |  | Intervention |
| action | VARCHAR(80) | Oui |  |  | Action tracée |

## journal_activite

| Champ | Type | Obligatoire | Clé | Défaut | Description |
|---|---|---|---|---|---|
| id | BIGINT | Oui | PK | auto | Identifiant |
| utilisateur | VARCHAR(120) | Oui |  |  | Acteur |
| action | VARCHAR(100) | Oui |  |  | Action |
