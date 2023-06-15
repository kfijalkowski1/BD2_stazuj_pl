package com.stazuj_pl.student;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.user.User;
import com.stazuj_pl.user.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class StudentHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserHandler userHandler;

    StudentHandler() {
        this.tableName = "Students";
        this.tableMainKey = "student_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Student.class);
    }

    public int getIdByUniqueField(String uniqueFieldValue) {
        String sql = "select user_id from Users where login = ?";
        List<Student> student = jdbcTemplate.query(sql, (BeanPropertyRowMapper) rowMapper, uniqueFieldValue);
        return student.get(0).getUser_student_id();
    }
    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }
    @Override
    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        List<String> obligatoryFields = Arrays.asList("hash_password", "name", "surname", "login", "academic_info_id");
        List<String> optionaryFields = Arrays.asList("mail", "photo_path", "about_me", "academic_year", "looking_for_job", "keywords");

        for(String key : obligatoryFields) {
            if(!data.containsKey(key)) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
        }
        for(String key : optionaryFields) {
            if(!data.containsKey(key)) {
                data.put(key, null);
            }
        }

        String sql_user = "INSERT INTO Users (mail, hash_password, name, surname, login, photo_path, about_me) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sql_student = "INSERT INTO Students (academic_info_id, user_student_id, academic_year, looking_for_job, keywords) VALUES (?, ?, ?, ?, ?)";

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

        if(addUser != 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
        int user_id = getIdByUniqueField(data.get("login").toString());

        int addStudent = jdbcTemplate.update(
                sql_student,
                data.get("academic_info_id").toString(),
                user_id,
                data.get("academic_year").toString(),
                data.get("looking_for_job").toString(),
                data.get("keywords").toString()
        );

        if (addStudent == 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        else {
            userHandler.deleteById(Integer.parseInt(data.get("user_id").toString()));
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> modifyEntity(Map<String, Object> data) {
        List<String> userKeys = Arrays.asList("name", "surname", "mail", "login", "photo_path", "about_me");
        List<String> studentKeys = Arrays.asList("academic_year", "looking_for_job", "keywords", "academic_info_id");

        List<EntityObj> listUsers = getAll();

        int affected = -1;
        for (String key : data.keySet()) {
            if (userKeys.contains(key)) {
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
            else if (studentKeys.contains(key)) {
                String sql = String.format("update Students set %s = ? where student_id = ?", key);
                affected = jdbcTemplate.update(sql, data.get(key).toString(), data.get("student_id").toString());
            }
        }
        if (affected == 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
    }
}
