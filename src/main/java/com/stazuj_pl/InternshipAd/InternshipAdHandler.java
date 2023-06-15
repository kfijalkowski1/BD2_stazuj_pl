package com.stazuj_pl.InternshipAd;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
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
public class InternshipAdHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    InternshipAdHandler() {
        this.tableName = "InternshipAds";
        this.tableMainKey = "intenship_ad_id";
        this.rowMapper = new BeanPropertyRowMapper<>(InternshipAd.class);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        InternshipAd ad = (InternshipAd) e;
        String sql = String.format("INSERT INTO %s (internship_description, publication_date, position_type, " +
                "salary_min, salary_max, employment_type, work_type, keywords, address_id, employee_id, expiration_date, duration) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName);
        int changedRows = jdbcTemplate.update(sql, ad.getInternship_description(),
                ad.getPublication_date(), ad.getPosition_type(), ad.getSalary_min(),
                ad.getSalary_max(), ad.getEmployment_type(), ad.getWork_type(), ad.getKeywords(), ad.getAddress_id(),
                ad.getEmployee_id(), ad.getExpiration_date(), ad.getDuration());
        return (changedRows == 1) ?
                new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        List<String> listOfKeys = Arrays.asList("internship_description", "position_type", "salary_min", "salary_max",
                "employment_type", "keywords", "address_id", "employee_id", "expiration_date", "duration");
        int affected = -1;
        for (String key : listOfKeys) {
            if (data.keySet().contains(key)) {
                String sql = String.format("update %s set %s = ? where %s = ?", tableName, key, tableMainKey);
                affected = jdbcTemplate.update(sql, data.get(key).toString(), data.get("user_id").toString());
                if (affected != 1) {
                    break;
                }
            }
        }
        return (affected == 1) ?
                new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
