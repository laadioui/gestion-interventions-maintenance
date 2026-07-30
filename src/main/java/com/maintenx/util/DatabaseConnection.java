package com.maintenx.util;

import com.maintenx.exception.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseConnection {
    private DatabaseConnection() {}
    public static Connection getConnection() {
        try {
            var cfg = ConfigLoader.load();
            return DriverManager.getConnection(cfg.getProperty("db.url"), cfg.getProperty("db.user"), cfg.getProperty("db.password"));
        } catch (SQLException e) {
            throw new DatabaseException("Connexion MySQL indisponible. Vérifiez config.properties et le service MySQL.", e);
        }
    }
}
