package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
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
}
