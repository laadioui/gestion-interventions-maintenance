package com.maintenx.dao.impl;
import com.maintenx.dao.InterventionDAO;
import com.maintenx.exception.DatabaseException;
import com.maintenx.model.Intervention;
import com.maintenx.model.enums.*;
import com.maintenx.service.InterventionSearchCriteria;
import java.sql.*;
import java.util.*;
public class InterventionDAOImpl extends AbstractJdbcDAO<Intervention> implements InterventionDAO {
    public Intervention save(Intervention i){String sql="INSERT INTO intervention(reference,titre,description,categorie,localisation,equipement,priorite,statut,cout_estime,cout_reel) VALUES(?,?,?,?,?,?,?,?,?,?)"; try(var c=connection();var ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){bind(ps,i.getReference(),i.getTitre(),i.getDescription(),i.getCategorie(),i.getLocalisation(),i.getEquipement(),i.getPriorite().name(),i.getStatut().name(),i.getCoutEstime(),i.getCoutReel());ps.executeUpdate();i.setId(generatedKey(ps));return i;}catch(SQLException e){throw new DatabaseException("Création intervention impossible.",e);}}
    public void update(Intervention i){execute("UPDATE intervention SET titre=?,description=?,categorie=?,localisation=?,equipement=?,priorite=?,statut=?,cout_estime=?,cout_reel=?,solution_appliquee=? WHERE id=?",i.getTitre(),i.getDescription(),i.getCategorie(),i.getLocalisation(),i.getEquipement(),i.getPriorite().name(),i.getStatut().name(),i.getCoutEstime(),i.getCoutReel(),i.getSolutionAppliquee(),i.getId());}
    public void deleteLogical(Long id){execute("UPDATE intervention SET statut='ANNULEE' WHERE id=?",id);}
    public Optional<Intervention> findById(Long id){return query("SELECT * FROM intervention WHERE id=?",this::map,id).stream().findFirst();}
    public List<Intervention> findAll(){return query("SELECT * FROM intervention ORDER BY date_creation DESC",this::map);}
    public List<Intervention> search(InterventionSearchCriteria c){StringBuilder sql=new StringBuilder("SELECT * FROM intervention WHERE 1=1"); var args=new ArrayList<>(); if(c.reference!=null&&!c.reference.isBlank()){sql.append(" AND reference LIKE ?");args.add("%"+c.reference+"%");} if(c.statut!=null){sql.append(" AND statut=?");args.add(c.statut.name());} if(c.priorite!=null){sql.append(" AND priorite=?");args.add(c.priorite.name());} return query(sql.toString(),this::map,args.toArray());}
    public String nextReference(){try(var c=connection();var ps=c.prepareStatement("SELECT COUNT(*)+1 FROM intervention");var rs=ps.executeQuery()){rs.next();return "INT-2026-"+String.format("%04d",rs.getInt(1));}catch(SQLException e){throw new DatabaseException("Référence impossible.",e);}}
    private void execute(String sql,Object...args){try(var c=connection();var ps=c.prepareStatement(sql)){bind(ps,args);ps.executeUpdate();}catch(SQLException e){throw new DatabaseException("Mise à jour intervention impossible.",e);}}
    private Intervention map(ResultSet r)throws SQLException{var i=new Intervention();i.setId(r.getLong("id"));i.setReference(r.getString("reference"));i.setTitre(r.getString("titre"));i.setDescription(r.getString("description"));i.setCategorie(r.getString("categorie"));i.setLocalisation(r.getString("localisation"));i.setEquipement(r.getString("equipement"));i.setPriorite(Priorite.valueOf(r.getString("priorite")));i.setStatut(StatutIntervention.valueOf(r.getString("statut")));i.setCommentaire(r.getString("commentaire"));i.setDiagnostic(r.getString("diagnostic"));i.setSolutionAppliquee(r.getString("solution_appliquee"));i.setCoutEstime(r.getBigDecimal("cout_estime"));i.setCoutReel(r.getBigDecimal("cout_reel"));return i;}
}
