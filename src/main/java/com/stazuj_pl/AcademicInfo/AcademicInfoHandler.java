package com.stazuj_pl.AcademicInfo;


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
public class AcademicInfoHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;

    AcademicInfoHandler() {
        this.tableName = "AcademicInfo";
        this.tableMainKey = "academic_info_id";
        this.rowMapper = new BeanPropertyRowMapper<>(AcademicInfo.class);
        this.modifiableKeys = Arrays.asList("college_name", "college_shortname", "faculty_name", "faculty_shortname", "course_name", "course_shortname", "course_description", "address_id");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            AcademicInfo academicInfo = (AcademicInfo) e;
            String sql = String.format("INSERT INTO %s (college_name, college_shortname, faculty_name, faculty_shortname, course_name, course_shortname, course_description, address_id)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, academicInfo.getCollege_name(), academicInfo.getCollege_shortname(), academicInfo.getFaculty_name(), academicInfo.getFaculty_shortname(),
                    academicInfo.getCourse_name(), academicInfo.getCourse_shortname(), academicInfo.getCourse_description(), academicInfo.getAddress_id());
            return (changedRows == 1) ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
