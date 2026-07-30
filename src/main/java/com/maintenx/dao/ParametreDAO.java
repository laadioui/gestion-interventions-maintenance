package com.maintenx.dao;
import java.util.Map;
public interface ParametreDAO { Map<String, String> findAll(); void save(String key, String value); }
