# Installation

## Prérequis

- Java 25 ou supérieur
- Maven 3.9+
- MySQL 8 si l'intégration base réelle est utilisée

## Étapes

1. Installer les dépendances avec `mvn clean test`.
2. Créer la base avec les scripts du dossier `database`.
3. Copier `config.properties.example` vers `config.properties`.
4. Générer le JAR avec `mvn clean package`.
5. Démarrer avec `java -jar target/maintenx.jar`.
