# Architecture MVC DAO

Le modèle MVC est appliqué avec une couche service intermédiaire :

`View -> Controller -> Service -> DAO -> MySQL`

Cette organisation évite la présence de requêtes SQL dans Swing et facilite les tests unitaires.
