package com.stazuj_pl.TaggedCandidates;


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
import java.util.List;

@Repository
public class TaggedCandidatesHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    TaggedCandidatesHandler() {
        this.tableName = "TaggedCandidates";
        this.tableMainKey = "tagged_candidate_id";
        this.rowMapper = new BeanPropertyRowMapper<>(TaggedCandidates.class);
        this.modifiableKeys = List.of("rating");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            TaggedCandidates cmt = (TaggedCandidates) e;
            String sql = String.format("INSERT INTO %s (rating, user_id_employee, internship_ad_id, user_id_student) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, cmt.getRating(), cmt.getUser_id_employee(),
                    cmt.getInternship_ad_id(), cmt.getUser_id_student());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
