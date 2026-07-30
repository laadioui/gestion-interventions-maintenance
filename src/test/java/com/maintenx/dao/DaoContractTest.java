package com.maintenx.dao;
import com.maintenx.dao.impl.UtilisateurDAOImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class DaoContractTest {
    @Test void daoJdbcExistePourIntegrationMysql() { assertNotNull(new UtilisateurDAOImpl()); }
}
