package com.stazuj_pl.Comments;


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
public class CommentsHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    CommentsHandler() {
        this.tableName = "Comments";
        this.tableMainKey = "comment_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Comments.class);
        this.modifiableKeys = Arrays.asList("content");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Comments cmt = (Comments) e;
            String sql = String.format("INSERT INTO %s (content, timestamp, post_id, user_id) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, cmt.getContent(), cmt.getTimestamp(),
                    cmt.getPost_id(), cmt.getUser_id());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
