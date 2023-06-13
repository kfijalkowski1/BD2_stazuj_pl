package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserHandler {
    private static BeanPropertyRowMapper rowMapper = new BeanPropertyRowMapper<>(User.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public User getUserInfo(int user_id) {
        String sql = "SELECT * FROM Users where user_id = ?";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("age", user_id);  // Bind the parameter value
        List<User> userData = jdbcTemplate.query(sql, (PreparedStatementSetter) paramMap, rowMapper);
//        if (userData.size() != 1) {
//            return false;
//        }
//        else {}//TODO
        return userData.get(0);
    }
}
