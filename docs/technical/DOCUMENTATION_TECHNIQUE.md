# Documentation technique

MaintenX est une application desktop Java 25. Le lancement initialise FlatLaf, charge les préférences utilisateur, crée les services et ouvre `LoginFrame`.

Les règles métier importantes sont centralisées dans `InterventionServiceImpl`, `UtilisateurServiceImpl` et `TechnicienServiceImpl`.
