package com.stazuj_pl.Employee;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserHandler userHandler;

    EmployeeHandler() {
        this.tableName = "Employees";
        this.tableMainKey = "employee_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        this.modifiableKeys = Arrays.asList("name", "surname", "mail", "login", "photo_path", "about_me",
                "message_template", "search_number", "plan_type", "company_id");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        try {
            List<String> obligatoryFields = Arrays.asList("hash_password", "name", "surname", "login", "company_id", "search_number", "plan_type");
            List<String> optionaryFields = Arrays.asList("mail", "photo_path", "about_me", "message_template");
            List<String> plan_typeEnum = Arrays.asList("regular", "premium", "ultimate");

            for (String key : obligatoryFields) {
                if (!data.containsKey(key)) {
                    return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
                }
            }

            if (!plan_typeEnum.contains(data.get("plan_type"))) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }

            for (String key : optionaryFields) {
                if (!data.containsKey(key)) {
                    data.put(key, null);
                }
            }

            String sql_user = "INSERT INTO Users (mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?)";
            String sql_employee = "INSERT INTO Employees (user_employee_id, company_id, message_template, search_number, plan_type) VALUES (?, ?, ?, ?, ?)";

            int addUser = jdbcTemplate.update(
                    sql_user,
                    data.get("mail").toString(),
                    data.get("hash_password").toString(),
                    data.get("name").toString(),
                    data.get("surname").toString(),
                    data.get("login").toString(),
                    data.get("photo_path").toString(),
                    data.get("about_me").toString()
            );

            if (addUser != 1) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
            int user_id = userHandler.getIdByUniqueField(data.get("login").toString());

            int addEmployee = jdbcTemplate.update(
                    sql_employee,
                    user_id,
                    data.get("company_id").toString(),
                    data.get("message_template").toString(),
                    data.get("search_number").toString(),
                    data.get("plan_type").toString()
            );

            if (addEmployee == 1) {
                return new ResponseEntity<HttpStatus>(HttpStatus.OK);
            } else {
                userHandler.deleteById(user_id);
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }
}
