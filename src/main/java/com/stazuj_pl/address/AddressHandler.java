package com.stazuj_pl.address;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.InternshipAd.InternshipAd;
import com.stazuj_pl.user.User;
import com.stazuj_pl.user.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class AddressHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    AddressHandler() {
        this.tableName = "Addresses";
        this.tableMainKey = "address_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Address.class);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        Address address = (Address) e;
        String sql = String.format("INSERT INTO %s (country_name, street_name, postal_code, house_nr) VALUES (?, ?, ?, ?)", tableName);
        int changedRows = jdbcTemplate.update(sql, address.getCountry_name(), address.getStreet_name(), address.getPostal_code(), address.getHouse_nr());
        return (changedRows == 1) ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    @Override //@TODO wywalic
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
