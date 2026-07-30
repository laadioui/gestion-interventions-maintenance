package com.maintenx.dao.impl;
import com.maintenx.dao.ParametreDAO;
import com.maintenx.exception.DatabaseException;
import java.sql.SQLException;
import java.util.*;
public class ParametreDAOImpl extends AbstractJdbcDAO<String> implements ParametreDAO {
    public Map<String,String> findAll(){var m=new LinkedHashMap<String,String>(); query("SELECT cle,valeur FROM parametre_application",r->r.getString("cle")+"="+r.getString("valeur")).forEach(s->{var p=s.split("=",2);m.put(p[0],p.length>1?p[1]:"");}); return m;}
    public void save(String key,String value){try(var c=connection();var ps=c.prepareStatement("INSERT INTO parametre_application(cle,valeur) VALUES(?,?) ON DUPLICATE KEY UPDATE valeur=VALUES(valeur)")){bind(ps,key,value);ps.executeUpdate();}catch(SQLException e){throw new DatabaseException("Paramètre impossible.",e);}}
}
