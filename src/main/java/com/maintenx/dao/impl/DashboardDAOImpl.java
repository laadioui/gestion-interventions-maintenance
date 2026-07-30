package com.maintenx.dao.impl;
import com.maintenx.dao.DashboardDAO;
import java.util.*;
public class DashboardDAOImpl extends AbstractJdbcDAO<Map.Entry<String,Long>> implements DashboardDAO {
    public Map<String, Long> countByStatus(){return count("SELECT statut k, COUNT(*) v FROM intervention GROUP BY statut");}
    public Map<String, Long> countByPriority(){return count("SELECT priorite k, COUNT(*) v FROM intervention GROUP BY priorite");}
    private Map<String,Long> count(String sql){var m=new LinkedHashMap<String,Long>(); query(sql,r->Map.entry(r.getString("k"),r.getLong("v"))).forEach(e->m.put(e.getKey(),e.getValue())); return m;}
}
