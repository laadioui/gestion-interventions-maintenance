# Base de données MaintenX

1. Créer la base : `mysql -u root -p < database/schema.sql`
2. Charger les données : `mysql -u root -p < database/sample_data.sql`
3. Créer les vues : `mysql -u root -p < database/views.sql`

Comptes de démonstration dans l'application embarquée :

| Rôle | Login | Mot de passe |
|---|---|---|
| Administrateur | `admin` | `Admin123!` |
| Responsable | `responsable` | `Resp123!` |
| Technicien | `tech` | `Tech123!` |
