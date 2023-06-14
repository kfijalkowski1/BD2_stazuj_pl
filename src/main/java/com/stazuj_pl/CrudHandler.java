package com.stazuj_pl;

import com.stazuj_pl.DBException;
import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;


@Repository
public abstract class CrudHandler {
    private static final BeanPropertyRowMapper<EntityObj> rowMapper = new BeanPropertyRowMapper<>(EntityObj.class);

    @Autowired
    JdbcTemplate jdbcTemplate;// = new JdbcTemplate();
    protected String tableName;
    protected String tableMainKey;

    public List<EntityObj> getAll() {
        String sql = String.format("SELECT * FROM %s", tableName);
        return new ArrayList<>(jdbcTemplate.query(sql, rowMapper));
    }

    public EntityObj getById(int entity_id) throws DBException {
        String sql = String.format("SELECT * FROM %s where %s = ?", tableName, tableMainKey);
        List<EntityObj> entityData = jdbcTemplate.query(sql, rowMapper, entity_id);
        if (entityData.size() != 1) {
            throw new DBException("Obj id not found in database");
        }
        return entityData.get(0);
    }

    public ResponseEntity<HttpStatus> deleteById(int entity_id) throws DBException {
        String sql = String.format("DELETE from %s where %s = ?;", tableName, tableMainKey);
        int dataAffected = jdbcTemplate.update(sql, entity_id);
        if (dataAffected != 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    public abstract ResponseEntity<HttpStatus> addEntity(EntityObj e);

    public abstract ResponseEntity<HttpStatus> modifyEntity(EntityObj e);
}
