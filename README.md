# MaintenX - Gestion des interventions de maintenance

MaintenX est une application de bureau Java Swing réalisée pour le stage de Othmane LAADIOUI chez Sirecom. Elle centralise les utilisateurs, techniciens, interventions, affectations, historiques, statistiques et exports CSV.

## Technologies

Java 25, Swing, FlatLaf, JDBC, MySQL 8, Maven, JUnit 5, JFreeChart, BCrypt.

## Architecture

L'application suit le flux `View -> Controller -> Service -> DAO -> MySQL`. Une couche de démonstration en mémoire permet de lancer immédiatement l'interface, tandis que les DAO JDBC et scripts SQL préparent l'intégration MySQL.

## Comptes de démonstration

| Rôle | Login | Mot de passe |
|---|---|---|
| Administrateur | `admin` | `Admin123!` |
| Responsable | `responsable` | `Resp123!` |
| Technicien | `tech` | `Tech123!` |

## Commandes

```bash
mvn clean test
mvn clean package
java -jar target/maintenx.jar
```

## Base MySQL

```bash
mysql -u root -p < database/schema.sql
mysql -u root -p < database/sample_data.sql
mysql -u root -p < database/views.sql
```

Copier `src/main/resources/config.properties.example` vers `src/main/resources/config.properties` et adapter les accès MySQL.

## Structure

- `src/main/java/com/maintenx` : application Java.
- `src/test/java/com/maintenx` : tests JUnit.
- `database` : scripts SQL.
- `docs/uml` : diagrammes PlantUML.
- `docs/user` : manuel utilisateur.
- `docs/report` : rapport de stage.
- `docs/technical` : documentation technique.

## Problèmes fréquents

- MySQL indisponible : vérifier `db.url`, `db.user`, `db.password`.
- JAR absent : lancer `mvn clean package`.
- Connexion refusée : utiliser les comptes de démonstration ci-dessus.

## Auteur

Othmane LAADIOUI, stagiaire. Encadrement : Anass El ghazi et Mme Safaa Fouad.
