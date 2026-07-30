package com.maintenx.service.impl;
import com.maintenx.service.ConfigurationService;
import com.maintenx.util.ConfigLoader;
import java.util.Properties;
public class ConfigurationServiceImpl implements ConfigurationService {
    public Properties load() { return ConfigLoader.load(); }
    public String appName() { return load().getProperty("app.name", "MaintenX"); }
    public String version() { return load().getProperty("app.version", "1.0.0"); }
}
