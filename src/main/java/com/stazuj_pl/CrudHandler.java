package com.stazuj_pl;

import com.stazuj_pl.DBException;
import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Repository
public abstract class CrudHandler {


    @Autowired
    JdbcTemplate jdbcTemplate;// = new JdbcTemplate();
    protected String tableName;
    protected String tableMainKey;
    protected Object rowMapper;

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

    public abstract ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data);
}
