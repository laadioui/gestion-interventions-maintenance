# Décisions techniques

- BCrypt a été ajouté pour éviter le stockage de mots de passe en clair.
- L'application embarque un jeu de données en mémoire pour faciliter la soutenance sans serveur MySQL disponible.
- Les DAO JDBC sont fournis pour l'intégration MySQL et respectent l'usage de `PreparedStatement`.
- Les statistiques de démonstration utilisent les données métier en mémoire ; les vues SQL fournies permettent le même calcul côté MySQL.
- Le thème et la taille de fenêtre sont mémorisés avec `java.util.prefs`.
