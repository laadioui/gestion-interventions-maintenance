package com.maintenx.dao.impl;
import com.maintenx.dao.HistoriqueInterventionDAO;
import com.maintenx.exception.DatabaseException;
import com.maintenx.model.HistoriqueIntervention;
import java.sql.*;
import java.util.*;
public class HistoriqueInterventionDAOImpl extends AbstractJdbcDAO<HistoriqueIntervention> implements HistoriqueInterventionDAO {
    public HistoriqueIntervention save(HistoriqueIntervention e){String sql="INSERT INTO historique_intervention(intervention_id,action,ancienne_valeur,nouvelle_valeur,utilisateur) VALUES(?,?,?,?,?)"; try(var c=connection();var ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){bind(ps,e.getInterventionId(),e.getAction(),e.getAncienneValeur(),e.getNouvelleValeur(),e.getUtilisateur());ps.executeUpdate();return new HistoriqueIntervention(generatedKey(ps),e.getInterventionId(),e.getAction(),e.getAncienneValeur(),e.getNouvelleValeur(),e.getUtilisateur());}catch(SQLException ex){throw new DatabaseException("Historique impossible.",ex);}}
    public void update(HistoriqueIntervention e){save(new HistoriqueIntervention(e.getId(),e.getInterventionId(),"CORRECTION",e.getAncienneValeur(),e.getNouvelleValeur(),e.getUtilisateur()));}
    public void deleteLogical(Long id){try(var c=connection();var ps=c.prepareStatement("INSERT INTO journal_activite(utilisateur,action,details) VALUES(?,?,?)")){bind(ps,"system","HISTORIQUE_DELETE_REFUSE","Historique non supprimable id="+id);ps.executeUpdate();}catch(SQLException ex){throw new DatabaseException("Journalisation impossible.",ex);}}
    public Optional<HistoriqueIntervention> findById(Long id){return findAll().stream().filter(h->h.getId()==id).findFirst();}
    public List<HistoriqueIntervention> findAll(){return query("SELECT * FROM historique_intervention ORDER BY date_action DESC",r->new HistoriqueIntervention(r.getLong("id"),r.getLong("intervention_id"),r.getString("action"),r.getString("ancienne_valeur"),r.getString("nouvelle_valeur"),r.getString("utilisateur")));}
    public List<HistoriqueIntervention> findByInterventionId(long id){return query("SELECT * FROM historique_intervention WHERE intervention_id=? ORDER BY date_action DESC",r->new HistoriqueIntervention(r.getLong("id"),r.getLong("intervention_id"),r.getString("action"),r.getString("ancienne_valeur"),r.getString("nouvelle_valeur"),r.getString("utilisateur")),id);}
}
