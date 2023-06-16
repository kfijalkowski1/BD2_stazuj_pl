package com.stazuj_pl.Files;


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
public class FilesHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    FilesHandler() {
        this.tableName = "Files";
        this.tableMainKey = "file_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Files.class);
        this.modifiableKeys = Arrays.asList("file_path", "is_main_cv", "file_type");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Files file = (Files) e;
            String sql = String.format("INSERT INTO %s (user_id, file_path, is_main_cv, file_type) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, file.getUser_id(), file.getFile_path(), file.getIs_main_cv(), file.getFile_type());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
