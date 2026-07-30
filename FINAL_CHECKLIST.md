# Checklist finale MaintenX

## Réalisé

- Projet Maven Java 25 avec `pom.xml`.
- Application Swing avec FlatLaf.
- Architecture `View -> Controller -> Service -> DAO -> MySQL`.
- Modèles, énumérations, exceptions, validations et utilitaires.
- Services métier avec données de démonstration en mémoire.
- DAO JDBC avec `PreparedStatement`.
- Authentification avec mots de passe BCrypt.
- Rôles administrateur, responsable et technicien.
- Écrans : connexion, fenêtre principale, dashboard, utilisateurs, techniciens, interventions, détails, affectation, historique, recherche, journal, profil, changement de mot de passe, paramètres, à propos.
- Dashboard avec graphiques JFreeChart.
- Export CSV des interventions.
- Scripts SQL MySQL.
- Tests JUnit 5.
- Diagrammes PlantUML.
- Documentation technique, installation, configuration, sécurité, manuel utilisateur, rapport de stage et plan de soutenance.
- Recherche `TODO`, `FIXME` et méthodes explicitement non implémentées : aucune occurrence trouvée.

## Commandes exécutées

| Commande | Résultat |
|---|---|
| `mvn clean test` | Échec environnement : `mvn` non reconnu |
| `winget install Apache.Maven --silent --accept-package-agreements --accept-source-agreements` | Échec source Windows : certificat `msstore` |
| `java -version` | Succès : OpenJDK Temurin 25.0.3 disponible |
| `where.exe mysql` | Échec environnement : client MySQL absent du PATH |
| `rg "TODO|FIXME|UnsupportedOperationException|méthode non implémentée" -n .` | Aucune occurrence |

## Commandes à exécuter sur un poste équipé

```bash
mvn clean test
mvn clean package
java -jar target/maintenx.jar
```

## Chemins importants

- JAR attendu : `target/maintenx.jar`
- Scripts SQL : `database/schema.sql`, `database/sample_data.sql`, `database/views.sql`, `database/reset_database.sql`
- Diagrammes UML : `docs/uml/`
- Rapport de stage : `docs/report/RAPPORT_STAGE.md`
- Manuel utilisateur : `docs/user/MANUEL_UTILISATEUR.md`
- Plan de soutenance : `docs/presentation/PLAN_SOUTENANCE.md`
- Configuration exemple : `src/main/resources/config.properties.example`

## Identifiants de démonstration

| Rôle | Login | Mot de passe |
|---|---|---|
| Administrateur | `admin` | `Admin123!` |
| Responsable | `responsable` | `Resp123!` |
| Technicien | `tech` | `Tech123!` |

## Limites restantes vérifiées

- Maven n'est pas disponible dans cet environnement, donc le JAR n'a pas pu être généré ici.
- Le client MySQL n'est pas disponible dans le PATH, donc les scripts SQL n'ont pas pu être importés localement.
- L'application démarre par défaut avec un jeu de données en mémoire pour faciliter la démonstration sans serveur MySQL.
- Les DAO JDBC et scripts MySQL sont présents pour brancher la persistance réelle.
