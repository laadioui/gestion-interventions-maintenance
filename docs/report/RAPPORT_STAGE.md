# Rapport de stage - MaintenX

## 1. Page de garde

Application Java de gestion des interventions de maintenance. Stagiaire : Othmane LAADIOUI. Entreprise : Sirecom. Encadrant d'entreprise : Anass El ghazi. Encadrante académique : Mme Safaa Fouad. Durée : 6 semaines.

## 2. Remerciements

Je remercie l'entreprise Sirecom, mon encadrant d'entreprise Anass El ghazi et mon encadrante académique Mme Safaa Fouad pour leur accompagnement.

## 3. Résumé

MaintenX centralise les demandes d'intervention de maintenance et remplace un suivi manuel par une application de bureau structurée.

## 4. Abstract

MaintenX is a Java desktop application dedicated to maintenance intervention management.

## 5. Liste des figures

[À compléter : captures des écrans principaux]

## 6. Liste des tableaux

Tableaux des besoins, des tests et du dictionnaire de données.

## 7. Liste des abréviations

DAO : Data Access Object. JDBC : Java Database Connectivity. UI : User Interface.

## 8. Sommaire

[À générer dans le document final]

## 9. Introduction générale

La maintenance nécessite un suivi fiable des demandes, priorités, techniciens et historiques.

## 10. Présentation de l'entreprise

[À compléter : présentation de l'entreprise]

## 11. Contexte général

Le projet répond au besoin d'une gestion centralisée et traçable des interventions.

## 12. Problématique

La gestion manuelle rend difficile la priorisation, l'affectation et l'analyse des interventions.

## 13. Analyse de l'existant

[À compléter : processus manuel observé]

## 14. Limites de l'existant

Risque d'oubli, absence de statistiques fiables, suivi d'historique limité.

## 15. Solution proposée

Une application Swing nommée MaintenX avec authentification, rôles, CRUD, affectation, historique, statistiques et export CSV.

## 16. Cahier des charges

Le cahier des charges impose Java 25, Swing, JDBC, MySQL, Maven, JUnit, FlatLaf et JFreeChart.

## 17. Besoins fonctionnels

Gestion des utilisateurs, techniciens, interventions, recherche, dashboard, historique et journaux.

## 18. Besoins non fonctionnels

Sécurité des mots de passe, interface lisible, architecture maintenable, tests automatisés.

## 19. Méthodologie de travail

Analyse, conception UML, implémentation par couches, tests, documentation.

## 20. Planning des six semaines

S1 analyse, S2 conception, S3 base et services, S4 interface, S5 tests, S6 documentation et soutenance.

## 21. Étude et conception UML

Les diagrammes PlantUML sont dans `docs/uml`.

## 22. Architecture MVC et DAO

Le flux `View -> Controller -> Service -> DAO -> MySQL` isole l'interface des règles métier et de la persistance.

## 23. Conception de la base de données

La base contient six tables principales avec clés étrangères et index de recherche.

## 24. Choix technologiques

Java Swing pour le desktop, FlatLaf pour l'apparence, JDBC pour la persistance, JUnit pour les tests.

## 25. Réalisation de l'application

Les modules réalisés couvrent la connexion, les CRUD et le suivi d'intervention.

## 26. Présentation des interfaces

[À insérer : capture de l'écran de connexion]

## 27. Gestion de la sécurité

Les mots de passe sont hachés avec BCrypt et les secrets sont exclus du dépôt.

## 28. Tests et validation

Les tests unitaires couvrent les validations et règles métier.

## 29. Difficultés rencontrées

Structurer une application complète sans framework web a demandé une séparation stricte des couches.

## 30. Solutions apportées

Une architecture MVC/Service/DAO compacte et testable a été appliquée.

## 31. Résultats obtenus

Un projet Maven exécutable et documenté a été produit.

## 32. Limites

Le mode par défaut utilise un magasin de démonstration en mémoire ; les DAO JDBC sont prêts pour l'intégration MySQL.

## 33. Perspectives d'amélioration

Connexion directe des services aux DAO JDBC, pagination avancée, génération PDF de rapports.

## 34. Conclusion générale

MaintenX constitue une base professionnelle pour présenter une application de gestion de maintenance.

## 35. Bibliographie

Documentation Java SE, Maven, JUnit.

## 36. Webographie

Documentation MySQL, FlatLaf, JFreeChart.

## 37. Annexes

Scripts SQL, diagrammes UML, plan de tests.
