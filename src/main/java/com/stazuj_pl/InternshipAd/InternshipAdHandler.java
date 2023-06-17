package com.stazuj_pl.InternshipAd;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.Employee.Employee;
import com.stazuj_pl.Employee.EmployeeHandler;
import com.stazuj_pl.EntityObj;
import org.hibernate.bytecode.enhance.internal.bytebuddy.EnhancerImpl;
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
public class InternshipAdHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EmployeeHandler employeeHandler;

    InternshipAdHandler() {
        this.tableName = "InternshipAds";
        this.tableMainKey = "internship_ad_id";
        this.safeName = "CensoredStudents";
        this.rowMapper = new BeanPropertyRowMapper<>(InternshipAd.class);
        this.modifiableKeys = Arrays.asList("internship_description", "publication_date", "position_type", "salary_min",
                "salary_max", "employment_type", "work_type", "keywords", "address_id", "expiration_date", "duration");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            InternshipAd ad = (InternshipAd) e;
        String sql = String.format("INSERT INTO %s (internship_description, publication_date, position_type, " +
                "salary_min, salary_max, employment_type, work_type, keywords, address_id, user_id, expiration_date, duration) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", tableName);
        int changedRows = jdbcTemplate.update(sql, ad.getInternship_description(),
                ad.getPublication_date(), ad.getPosition_type(), ad.getSalary_min(),
                ad.getSalary_max(), ad.getEmployment_type(), ad.getWork_type(), ad.getKeywords(), ad.getAddress_id(),
                ad.getUser_id(), ad.getExpiration_date(), ad.getDuration());
        return (changedRows == 1) ?
                new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (
        DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        try {
            EntityObj adObj = getById(Integer.parseInt(data.get("internship_ad_id").toString()));
            InternshipAd ad = (InternshipAd) adObj;
            if(!data.containsKey("user_id")) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }

            if(ad.getUser_id() != Integer.parseInt(data.get("user_id").toString())) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }

            int affected = -1;
            for (String key : modifiableKeys) {
                if (data.keySet().contains(key)) {
                    String sql = String.format("update %s set %s = ? where %s = ?", tableName, key, tableMainKey);
                    affected = jdbcTemplate.update(sql, data.get(key).toString(), data.get(tableMainKey).toString());
                    if (affected != 1) {
                        break;
                    }
                }
            }
            return (affected == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
