package com.stazuj_pl.Addresses;


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
import java.util.Map;

@Repository
public class AddressesHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    AddressesHandler() {
        this.tableName = "Addresses";
        this.tableMainKey = "address_Id";
        this.rowMapper = new BeanPropertyRowMapper<>(Addresses.class);
        this.modifiableKeys = Arrays.asList("country_name", "street_name", "postal_code", "house_nr");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Addresses add = (Addresses) e;
            String sql = String.format("INSERT INTO %s (country_name, street_name, postal_code, house_nr) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, add.getCountry_name(), add.getStreet_name(),
                    add.getPostal_code(), add.getHouse_nr());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
