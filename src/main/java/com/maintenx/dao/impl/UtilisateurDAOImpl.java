package com.maintenx.dao.impl;
import com.maintenx.dao.UtilisateurDAO;
import com.maintenx.exception.DatabaseException;
import com.maintenx.model.Utilisateur;
import com.maintenx.model.enums.Role;
import java.sql.*;
import java.util.*;
public class UtilisateurDAOImpl extends AbstractJdbcDAO<Utilisateur> implements UtilisateurDAO {
    public Utilisateur save(Utilisateur u){String sql="INSERT INTO utilisateur(nom,prenom,email,nom_utilisateur,mot_de_passe_hash,role,telephone,actif) VALUES(?,?,?,?,?,?,?,?)"; try(var c=connection();var ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){bind(ps,u.getNom(),u.getPrenom(),u.getEmail(),u.getNomUtilisateur(),u.getMotDePasseHash(),u.getRole().name(),u.getTelephone(),u.isActif());ps.executeUpdate();u.setId(generatedKey(ps));return u;}catch(SQLException e){throw new DatabaseException("Création utilisateur impossible.",e);}}
    public void update(Utilisateur u){execute("UPDATE utilisateur SET nom=?,prenom=?,email=?,nom_utilisateur=?,role=?,telephone=?,actif=? WHERE id=?",u.getNom(),u.getPrenom(),u.getEmail(),u.getNomUtilisateur(),u.getRole().name(),u.getTelephone(),u.isActif(),u.getId());}
    public void deleteLogical(Long id){execute("UPDATE utilisateur SET actif=false WHERE id=?",id);}
    public Optional<Utilisateur> findById(Long id){return query("SELECT * FROM utilisateur WHERE id=?",this::map,id).stream().findFirst();}
    public List<Utilisateur> findAll(){return query("SELECT * FROM utilisateur ORDER BY id",this::map);}
    public Optional<Utilisateur> findByUsername(String username){return query("SELECT * FROM utilisateur WHERE nom_utilisateur=?",this::map,username).stream().findFirst();}
    public boolean existsByEmail(String email){return !query("SELECT * FROM utilisateur WHERE email=?",this::map,email).isEmpty();}
    public boolean existsByUsername(String username){return !query("SELECT * FROM utilisateur WHERE nom_utilisateur=?",this::map,username).isEmpty();}
    private void execute(String sql,Object...args){try(var c=connection();var ps=c.prepareStatement(sql)){bind(ps,args);ps.executeUpdate();}catch(SQLException e){throw new DatabaseException("Mise à jour utilisateur impossible.",e);}}
    private Utilisateur map(ResultSet r)throws SQLException{return new Utilisateur(r.getLong("id"),r.getString("nom"),r.getString("prenom"),r.getString("email"),r.getString("nom_utilisateur"),r.getString("mot_de_passe_hash"), Role.valueOf(r.getString("role")),r.getString("telephone"),r.getBoolean("actif"));}
}
