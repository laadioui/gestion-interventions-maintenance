package com.maintenx.dao;
import java.util.Map;
public interface DashboardDAO { Map<String, Long> countByStatus(); Map<String, Long> countByPriority(); }
