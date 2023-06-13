package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Configurable
public class UserHandler {
    private static final BeanPropertyRowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
    @Autowired
    private static JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserInfo(int user_id) {

        String sql = "SELECT * FROM Users where user_id = ?";
        List<User> userData = jdbcTemplate.query(sql, rowMapper, user_id); // if many Object[] {user_id...}
//        if (userData.size() != 1) {
//            return false;
//        }
//        else {}//TODO
        return userData.get(0);
    }
}
