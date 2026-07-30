# Plan de tests

## Objectifs

Vérifier les validations, les règles métier et la non-régression de la compilation.

## Environnement

Java 25, Maven, JUnit 5. Les tests unitaires utilisent le magasin de données en mémoire.

## Cas principaux

| Cas | Résultat attendu |
|---|---|
| Email valide | Accepté |
| Email invalide | ValidationException |
| Mot de passe court | ValidationException |
| Coût négatif | ValidationException |
| Création utilisateur | Identifiant généré |
| Doublon utilisateur | DuplicateResourceException |
| Affectation technicien actif | Statut AFFECTEE |
| Technicien inactif | BusinessException |
| Clôture sans solution | ValidationException |

## Critères d'acceptation

`mvn clean test` doit se terminer avec succès.
