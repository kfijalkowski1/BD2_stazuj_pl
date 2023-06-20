package com.stazuj_pl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;


@Repository
public abstract class CrudHandler {


    @Autowired
    JdbcTemplate jdbcTemplate;
    protected String tableName;
    protected String safeName = null;
    protected String tableMainKey;
    protected Object rowMapper;
    protected Object safeRowMapper = null;
    protected List<String> modifiableKeys;
    private List<String> statsCountSearches = Arrays.asList("Users", "Companies", "InternshipAds", "Posts");

    public List<EntityObj> getAll() {
        safeName = (safeName == null) ? tableName : safeName;
        safeRowMapper = (safeRowMapper == null) ? rowMapper : safeRowMapper;
        String sql = String.format("SELECT * FROM %s", safeName);
        return jdbcTemplate.query(sql, (BeanPropertyRowMapper) safeRowMapper);
    }

    public EntityObj getById(int entity_id) {
        safeName = (safeName == null) ? tableName : safeName;
        safeRowMapper = (safeRowMapper == null) ? rowMapper : safeRowMapper;
        String sql = String.format("SELECT * FROM %s where %s = ?", safeName, tableMainKey);
        if (statsCountSearches.contains(tableName)) {
            updateStats(tableName, "views", entity_id);
        }
        List<EntityObj> entityData = jdbcTemplate.query(sql, (BeanPropertyRowMapper) safeRowMapper, entity_id);
        return (entityData.size() != 1) ? null : entityData.get(0);
    }

    public ResponseEntity<HttpStatus> deleteById(int entity_id) {
        String sql = String.format("DELETE from %s where %s = ?;", tableName, tableMainKey);
        int dataAffected = jdbcTemplate.update(sql, entity_id);
        if (dataAffected != 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    

    public abstract ResponseEntity<HttpStatus> addEntity(EntityObj e);

    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        try {
            int affected = -1;
            for (String key : modifiableKeys) {
                if (data.keySet().contains(key)) {
                    String sql = String.format("update %s set %s = ? where %s = ?", tableName, key, tableMainKey);
                    affected = jdbcTemplate.update(sql, data.get(key).toString(), data.get(tableMainKey).toString());
                    if (affected != 1) {
                        break;
                    }
                }
            }
            return (affected == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

        private void updateStats(String table_name, String type, int id) {
        List<SqlParameter> parameters = Arrays.asList(new SqlParameter(Types.VARCHAR));
        jdbcTemplate.call(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                CallableStatement cs = con.prepareCall("{call updateStatistics(?, ?, ?)}");
                cs.setString(1, table_name);
                cs.setString(2, type);
                cs.setInt(3, id);
                return cs;
            }
        }, parameters);
    }

}
