package com.stazuj_pl.academicInfo;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.address.Address;
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
public class AcademicInfoHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AcademicInfoHandler() {
        this.tableName = "AcademicInfo";
        this.tableMainKey = "academic_info_id";
        this.rowMapper = new BeanPropertyRowMapper<>(AcademicInfo.class);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        AcademicInfo academicInfo = (AcademicInfo) e;
        String sql = String.format("INSERT INTO %s (college_name, college_shortname, faculty_name, faculty_shortname, course_name, course_shortname, course_description, address_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?)", tableName);
        int changedRows = jdbcTemplate.update(sql, academicInfo.getCollege_name(), academicInfo.getCollege_shortname(), academicInfo.getFaculty_name(), academicInfo.getFaculty_shortname(),
                academicInfo.getCourse_name(), academicInfo.getCourse_shortname(), academicInfo.getCourse_description(), academicInfo.getAddress_id());
        return (changedRows == 1) ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
