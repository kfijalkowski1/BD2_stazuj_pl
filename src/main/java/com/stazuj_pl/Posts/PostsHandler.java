package com.stazuj_pl.Posts;


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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Repository
public class PostsHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    PostsHandler() {
        this.tableName = "Posts";
        this.tableMainKey = "post_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Posts.class);
        this.modifiableKeys = Arrays.asList("title", "content", "type");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Posts post = (Posts) e;
            String sql = String.format("INSERT INTO %s (author_id, title, content, type, publication_date) " +
                    "VALUES (?, ?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, post.getAuthor_id(), post.getTitle(), post.getContent(), post.getType(), post.getPublication_date());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
