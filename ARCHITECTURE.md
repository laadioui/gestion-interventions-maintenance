# Architecture

MaintenX sépare les responsabilités :

- `view` : Swing, FlatLaf, tables, formulaires et dialogues.
- `controller` : adaptation des événements UI vers les services.
- `service` : règles métier, validations et permissions.
- `dao` : contrats de persistance.
- `dao.impl` : JDBC avec `PreparedStatement`.
- `model` : objets métier.
- `validation` : validations réutilisables.
- `util` : configuration, logs, préférences, sécurité.

Les vues n'appellent jamais directement les DAO.
