package com.stazuj_pl.user;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mysql.cj.xdevapi.JsonParser;
import com.mysql.cj.xdevapi.JsonString;
import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import java.io.Console;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class UserHandler extends CrudHandler {
    private static final BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

    UserHandler() {
        this.tableName = "Users";
        this.tableMainKey = "user_id";
    }

    public ResponseEntity<HttpStatus> addEntity(User user) {
        if (!userExists(user.getUser_id())) {
            String sql = "INSERT INTO Users (user_id, mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, user.getUser_id(), user.getMail(), user.getHash_password(), user.getName(), user.getSurname(), user.getLogin(), user.getPhoto_path(), user.getAbout_me());
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }

    public boolean userExists(int id) {
        List<EntityObj> userList = getAll();
        for (User user : userList) {
            if (user.getUser_id() == id) {
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<HttpStatus> setUserField(Map<String, Object> data) {
        List<String> listOfKeys = Arrays.asList("name", "surname", "mail", "login", "photo_path", "about_me");
        int affected = -1;
        String field = data.get("field").toString();

        if (listOfKeys.contains(field)) {
            String sql = "update Users set %s = ? where user_id = ?".formatted(field);
            affected = jdbcTemplate.update(sql, data.get("value").toString(), data.get("user_id").toString());
        }

        if (affected <= 1 && affected >= 0) {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
