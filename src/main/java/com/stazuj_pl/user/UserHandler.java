package com.stazuj_pl.user;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    UserHandler() {
        this.tableName = "Users";
        this.tableMainKey = "user_id";
        this.rowMapper = new BeanPropertyRowMapper<>(User.class);
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        User user = (User) e;
        String sql = "INSERT INTO Users (user_id, mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        List<EntityObj> listUsers = getAll();
        for(EntityObj obj : listUsers)
        {
            User query_user = (User) obj;
            if(query_user.getMail().equals(user.getMail()) || query_user.getLogin().equals(user.getLogin()) || query_user.getUser_id() == user.getUser_id()) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
        }
        int changedRows = jdbcTemplate.update(sql, user.getUser_id(), user.getMail(), user.getHash_password(), user.getName(), user.getSurname(), user.getLogin(), user.getPhoto_path(), user.getAbout_me());

        return (changedRows == 1) ? new ResponseEntity<HttpStatus>(HttpStatus.OK) : new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        List<String> listOfKeys = Arrays.asList("name", "surname", "mail", "login", "photo_path", "about_me");
        List<EntityObj> listUsers = getAll();

        int affected = -1;
        for (String key : listOfKeys) {
            if (data.keySet().contains(key)) {

                for(EntityObj query_user : listUsers) {
                    User user = (User) query_user;
                    if(key.equals("mail") && user.getMail().equals(data.get("mail").toString())) {
                        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
                    }
                    if(key.equals("login") && user.getMail().equals(data.get("login").toString())) {
                        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
                    }
                }

                String sql = String.format("update Users set %s = ? where user_id = ?", key);
                affected = jdbcTemplate.update(sql, data.get(key).toString(), data.get("user_id").toString());
            }
        }
        if (affected == 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

}
