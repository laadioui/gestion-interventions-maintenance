package com.maintenx.service;
import java.util.Properties;
public interface ConfigurationService { Properties load(); String appName(); String version(); }
