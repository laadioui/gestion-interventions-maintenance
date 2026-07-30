package com.maintenx.util;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.util.prefs.Preferences;

public final class AppPreferences {
    private static final Preferences PREFS = Preferences.userNodeForPackage(AppPreferences.class);
    private AppPreferences() {}
    public static void applySavedTheme() {
        try {
            if ("dark".equals(PREFS.get("theme", "light"))) FlatDarkLaf.setup(); else FlatLightLaf.setup();
        } catch (Exception ignored) { FlatLightLaf.setup(); }
    }
    public static void saveTheme(boolean dark) { PREFS.put("theme", dark ? "dark" : "light"); }
    public static Dimension windowSize() { return new Dimension(PREFS.getInt("width", 1180), PREFS.getInt("height", 760)); }
    public static void saveWindowSize(Dimension d) { PREFS.putInt("width", d.width); PREFS.putInt("height", d.height); }
}
