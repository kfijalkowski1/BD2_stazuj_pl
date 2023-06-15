package com.stazuj_pl.Companies;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class CompaniesHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    CompaniesHandler() {
        this.tableName = "Companies";
        this.tableMainKey = "company_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Companies.class);
        this.modifiableKeys = Arrays.asList("name", "about_me", "logo_path");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Companies cmt = (Companies) e;
            String sql = String.format("INSERT INTO %s (name, logo_path, about_me) " +
                    "VALUES (?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, cmt.getName(), cmt.getLogo_path(),
                    cmt.getAbout_me());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
