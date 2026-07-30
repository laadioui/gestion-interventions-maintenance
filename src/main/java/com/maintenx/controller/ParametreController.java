package com.maintenx.controller;
import com.maintenx.service.ConfigurationService;
public class ParametreController {
    private final ConfigurationService configuration;
    public ParametreController(ConfigurationService configuration) { this.configuration = configuration; }
    public String version() { return configuration.version(); }
}
