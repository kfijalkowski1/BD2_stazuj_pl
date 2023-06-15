package com.stazuj_pl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.*;


@Repository
public abstract class CrudHandler {


    @Autowired
    JdbcTemplate jdbcTemplate;// = new JdbcTemplate();
    protected String tableName;
    protected String tableMainKey;
    protected Object rowMapper;
    protected List<String> modifiableKeys;

    public List<EntityObj> getAll() {
        String sql = String.format("SELECT * FROM %s", tableName);
        return jdbcTemplate.query(sql, (BeanPropertyRowMapper) rowMapper);
    }

    public EntityObj getById(int entity_id) {
        String sql = String.format("SELECT * FROM %s where %s = ?", tableName, tableMainKey);
        List<EntityObj> entityData = jdbcTemplate.query(sql, (BeanPropertyRowMapper) rowMapper, entity_id);
//        if (entityData.size() != 1) {
//            throw new DBException("Obj id not found in database");
//        }
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
                    affected = jdbcTemplate.update(sql, data.get(key).toString());
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
}
