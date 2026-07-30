package com.maintenx.dao.impl;
import com.maintenx.exception.DatabaseException;
import com.maintenx.util.DatabaseConnection;
import java.sql.*;
import java.util.*;
abstract class AbstractJdbcDAO<T> {
    protected Connection connection() { return DatabaseConnection.getConnection(); }
    protected long generatedKey(PreparedStatement ps) throws SQLException { try (ResultSet rs = ps.getGeneratedKeys()) { return rs.next() ? rs.getLong(1) : 0L; } }
    protected List<T> query(String sql, Mapper<T> mapper, Object... args) {
        try (var c = connection(); var ps = c.prepareStatement(sql)) {
            bind(ps, args); try (var rs = ps.executeQuery()) { var out = new ArrayList<T>(); while (rs.next()) out.add(mapper.map(rs)); return out; }
        } catch (SQLException e) { throw new DatabaseException("Erreur SQL.", e); }
    }
    protected void bind(PreparedStatement ps, Object... args) throws SQLException { for (int i=0;i<args.length;i++) ps.setObject(i+1,args[i]); }
    @FunctionalInterface interface Mapper<T> { T map(ResultSet rs) throws SQLException; }
}
