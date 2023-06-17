package com.stazuj_pl.UserOpinions;


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
public class UserOpinionsHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    UserOpinionsHandler() {
        this.tableName = "UserOpinions";
        this.tableMainKey = "opinion_id";
        this.rowMapper = new BeanPropertyRowMapper<>(UserOpinions.class);
        this.modifiableKeys = Arrays.asList("content", "rating");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            UserOpinions userOpinions = (UserOpinions) e;
            String sql = String.format("INSERT INTO %s (user_id, company_id, rating, content) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, userOpinions.getUser_id(), userOpinions.getCompany_id(), userOpinions.getRating(), userOpinions.getContent());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
