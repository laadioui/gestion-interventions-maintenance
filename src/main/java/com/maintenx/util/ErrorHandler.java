package com.maintenx.util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.*;

public final class ErrorHandler {
    private static final Logger LOGGER = Logger.getLogger("MaintenX");
    static {
        try {
            Files.createDirectories(Path.of("logs"));
            LOGGER.addHandler(new FileHandler("logs/maintenx.log", true));
        } catch (IOException ignored) { }
    }
    private ErrorHandler() {}
    public static void show(Component parent, String message, Throwable t) {
        LOGGER.log(Level.WARNING, message, t);
        JOptionPane.showMessageDialog(parent, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }
    public static void showFatal(Component parent, String message, Throwable t) {
        LOGGER.log(Level.SEVERE, message, t);
        JOptionPane.showMessageDialog(parent, message, "Erreur critique", JOptionPane.ERROR_MESSAGE);
    }
}
