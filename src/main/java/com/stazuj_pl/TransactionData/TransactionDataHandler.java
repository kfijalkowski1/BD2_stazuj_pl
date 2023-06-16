package com.stazuj_pl.TransactionData;


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
public class TransactionDataHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    TransactionDataHandler() {
        this.tableName = "TransactionData";
        this.tableMainKey = "transaction_data_id";
        this.rowMapper = new BeanPropertyRowMapper<>(TransactionData.class);
        this.modifiableKeys = Arrays.asList("state");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            TransactionData transactionData = (TransactionData) e;
            String sql = String.format("INSERT INTO %s (state, internship_ad_id, cv_id) " +
                    "VALUES (?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, transactionData.getState(), transactionData.getInternship_ad_id(), transactionData.getCv_id());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
