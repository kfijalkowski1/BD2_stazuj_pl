package com.stazuj_pl.TaggedOffers;


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
public class TaggedOffersHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    TaggedOffersHandler() {
        this.tableName = "TaggedOffers";
        this.tableMainKey = "tagged_offer_id";
        this.rowMapper = new BeanPropertyRowMapper<>(TaggedOffers.class);
        this.modifiableKeys = List.of();
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            TaggedOffers taggedOffer = (TaggedOffers) e;
            String sql = String.format("INSERT INTO %s (internship_ad_id, student_id) VALUES (?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, taggedOffer.getInternship_ad_id(), taggedOffer.getStudent_id());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
