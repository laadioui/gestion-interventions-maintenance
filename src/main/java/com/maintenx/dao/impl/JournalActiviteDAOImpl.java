package com.maintenx.dao.impl;
import com.maintenx.dao.JournalActiviteDAO;
import com.maintenx.exception.DatabaseException;
import com.maintenx.model.JournalActivite;
import java.sql.*;
import java.util.*;
public class JournalActiviteDAOImpl extends AbstractJdbcDAO<JournalActivite> implements JournalActiviteDAO {
    public JournalActivite save(JournalActivite e){String sql="INSERT INTO journal_activite(utilisateur,action,details) VALUES(?,?,?)"; try(var c=connection();var ps=c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)){bind(ps,e.getUtilisateur(),e.getAction(),e.getDetails());ps.executeUpdate();return new JournalActivite(generatedKey(ps),e.getUtilisateur(),e.getAction(),e.getDetails());}catch(SQLException ex){throw new DatabaseException("Journalisation impossible.",ex);}}
    public void update(JournalActivite e){save(new JournalActivite(0,e.getUtilisateur(),"CORRECTION_JOURNAL",e.getDetails()));}
    public void deleteLogical(Long id){save(new JournalActivite(0,"system","JOURNAL_DELETE_REFUSE","Journal non supprimable id="+id));}
    public Optional<JournalActivite> findById(Long id){return findAll().stream().filter(j->j.getId()==id).findFirst();}
    public List<JournalActivite> findAll(){return query("SELECT * FROM journal_activite ORDER BY date_action DESC",r->new JournalActivite(r.getLong("id"),r.getString("utilisateur"),r.getString("action"),r.getString("details")));}
}
