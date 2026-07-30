package com.maintenx.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class ConfigLoader {
    private ConfigLoader() {}
    public static Properties load() {
        var props = new Properties();
        try (InputStream in = Files.exists(Path.of("src/main/resources/config.properties"))
                ? Files.newInputStream(Path.of("src/main/resources/config.properties"))
                : ConfigLoader.class.getResourceAsStream("/config.properties.example")) {
            if (in != null) props.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Configuration illisible.", e);
        }
        return props;
    }
}
