package com.stazuj_pl.Messages;


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
public class MessagesHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    MessagesHandler() {
        this.tableName = "Messages";
        this.tableMainKey = "message_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Messages.class);
        this.modifiableKeys = Arrays.asList("content");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        try {
            Messages cmt = (Messages) e;
            String sql = String.format("INSERT INTO %s (content, sent_time, author_id, receiver_id) " +
                    "VALUES (?, ?, ?, ?)", tableName);
            int changedRows = jdbcTemplate.update(sql, cmt.getContent(), cmt.getSent_time(),
                    cmt.getAuthor_id(), cmt.getReceiver_id());
            return (changedRows == 1) ?
                    new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        } catch (DataAccessException er) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }

}
