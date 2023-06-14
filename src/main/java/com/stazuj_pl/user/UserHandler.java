package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

//@Service
//@Configurable
@Repository
public class UserHandler {
    private static final BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);

    @Autowired
    JdbcTemplate jdbcTemplate;// = new JdbcTemplate();

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return new ArrayList<>(jdbcTemplate.query(sql, rowMapper));
    }

    public User getUserById(int user_id) {
        String sql = "SELECT * FROM Users where user_id = ?";
        List<User> userData = jdbcTemplate.query(sql, rowMapper, user_id);
//        if (userData.size() != 1) {
//            throw new Exception("AAAA");
//        }
//        else {}//TODO
        return userData.get(0);
    }

    public ResponseEntity<HttpStatus> createUser(User user) {
        String sql = "INSERT INTO Users (user_id, mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUser_id(), user.getMail(), user.getHash_password(), user.getName(), user.getSurname(), user.getLogin(), user.getPhoto_path(), user.getAbout_me());
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> setUserField(int id, String field, String value) {
        User user = getUserById(id);
        String sql = "UPDATE Users set ? = ? where user_id = ?";
        jdbcTemplate.update(sql, field, value, id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}
