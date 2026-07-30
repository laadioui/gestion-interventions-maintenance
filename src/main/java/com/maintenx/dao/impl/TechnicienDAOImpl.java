package com.maintenx.dao.impl;
import com.maintenx.dao.TechnicienDAO;
import com.maintenx.exception.DatabaseException;
import com.maintenx.model.Technicien;
import com.maintenx.model.enums.Specialite;
import java.sql.*;
import java.util.*;
public class TechnicienDAOImpl extends AbstractJdbcDAO<Technicien> implements TechnicienDAO {
    public Technicien save(Technicien t){String sql="INSERT INTO technicien(matricule,nom,prenom,email,telephone,specialite,disponible,actif) VALUES(?,?,?,?,?,?,?,?)"; try(var c=connection();var ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){bind(ps,t.getMatricule(),t.getNom(),t.getPrenom(),t.getEmail(),t.getTelephone(),t.getSpecialite().name(),t.isDisponible(),t.isActif());ps.executeUpdate();t.setId(generatedKey(ps));return t;}catch(SQLException e){throw new DatabaseException("Création technicien impossible.",e);}}
    public void update(Technicien t){execute("UPDATE technicien SET matricule=?,nom=?,prenom=?,email=?,telephone=?,specialite=?,disponible=?,actif=? WHERE id=?",t.getMatricule(),t.getNom(),t.getPrenom(),t.getEmail(),t.getTelephone(),t.getSpecialite().name(),t.isDisponible(),t.isActif(),t.getId());}
    public void deleteLogical(Long id){execute("UPDATE technicien SET actif=false, disponible=false WHERE id=?",id);}
    public Optional<Technicien> findById(Long id){return query("SELECT * FROM technicien WHERE id=?",this::map,id).stream().findFirst();}
    public List<Technicien> findAll(){return query("SELECT * FROM technicien ORDER BY id",this::map);}
    public boolean existsByMatricule(String matricule){return !query("SELECT * FROM technicien WHERE matricule=?",this::map,matricule).isEmpty();}
    private void execute(String sql,Object...args){try(var c=connection();var ps=c.prepareStatement(sql)){bind(ps,args);ps.executeUpdate();}catch(SQLException e){throw new DatabaseException("Mise à jour technicien impossible.",e);}}
    private Technicien map(ResultSet r)throws SQLException{return new Technicien(r.getLong("id"),r.getString("matricule"),r.getString("nom"),r.getString("prenom"),r.getString("email"),r.getString("telephone"), Specialite.valueOf(r.getString("specialite")),r.getBoolean("disponible"),r.getBoolean("actif"));}
}
