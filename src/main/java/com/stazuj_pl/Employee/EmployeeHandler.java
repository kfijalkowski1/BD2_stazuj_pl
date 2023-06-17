package com.stazuj_pl.Employee;


import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.InternshipAd.InternshipAd;
import com.stazuj_pl.InternshipAd.InternshipAdHandler;
import com.stazuj_pl.Student.Student;
import com.stazuj_pl.TaggedCandidates.TaggedCandidates;
import com.stazuj_pl.TaggedCandidates.TaggedCandidatesHandler;
import com.stazuj_pl.TaggedOffers.TaggedOffers;
import com.stazuj_pl.TaggedOffers.TaggedOffersHandler;
import com.stazuj_pl.TransactionData.TransactionData;
import com.stazuj_pl.TransactionData.TransactionDataHandler;
import com.stazuj_pl.User.User;
import com.stazuj_pl.User.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserHandler userHandler;
    @Autowired
    TaggedCandidatesHandler taggedCandidatesHandler;
    @Autowired
    TransactionDataHandler transactionDataHandler;
    @Autowired
    InternshipAdHandler internshipAdHandler;

    EmployeeHandler() {
        this.tableName = "Employees";
        this.safeName = "CensoredEmployees";
        this.safeRowMapper = new BeanPropertyRowMapper<>(CensoredEmployee.class);
        this.tableMainKey = "employee_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Employee.class);
        this.modifiableKeys = Arrays.asList("message_template", "search_number", "plan_type", "company_id");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(int entity_id) {
        EntityObj obj = getById(entity_id);
        Employee employee = (Employee) obj;
        int employee_id = employee.getEmployee_id();
        String delete_employee = String.format("DELETE from %s where %s = ?;", tableName, tableMainKey);
        int dataAffected = jdbcTemplate.update(delete_employee, employee_id);
        if (dataAffected != 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    public List<Integer> getTransactionDataForAd(Map<String, Integer> data) {

        List<Integer> listOfTransactionDataId = new ArrayList<>(List.of());
        List<EntityObj> listOfTransactionData = transactionDataHandler.getAll();
        for (EntityObj obj : listOfTransactionData) {
            TransactionData transactionData = (TransactionData) obj;
            EntityObj adObj = internshipAdHandler.getById(transactionData.getInternship_ad_id());
            InternshipAd ad = (InternshipAd) adObj;

            boolean condition;

            if(data.containsKey("internship_ad_id")) {
                condition = ad.getUser_id() == Integer.parseInt(data.get("user_id").toString()) && ad.getInternship_ad_id() == Integer.parseInt(data.get("internship_ad_id").toString());
            }
            else {
                condition = ad.getUser_id() == Integer.parseInt(data.get("user_id").toString());
            }

            if (condition) {
                listOfTransactionDataId.add(transactionData.getTransaction_data_id());
            }

        }
        return listOfTransactionDataId;
    }
    @Override
    public EntityObj getById(int entity_id) {
        String sql = String.format("SELECT * FROM %s where user_employee_id = ?", safeName, tableMainKey);
        List<EntityObj> entityData = jdbcTemplate.query(sql, (BeanPropertyRowMapper) safeRowMapper, entity_id);
        return (entityData.size() != 1) ? null : entityData.get(0);
    }

    public List<Integer> getTaggedCandidatesById(int id) {
        List<EntityObj> listOfTaggedCandidates = taggedCandidatesHandler.getAll();
        List<Integer> listOfCandidatesId = new ArrayList<>(List.of());

        for(EntityObj obj : listOfTaggedCandidates) {
            TaggedCandidates taggedCandidates = (TaggedCandidates) obj;
            if(taggedCandidates.getUser_id_employee().equals(Integer.toString(id))) {
                listOfCandidatesId.add(Integer.parseInt(taggedCandidates.getUser_id_student()));
            }
        }
        return listOfCandidatesId;
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

            User user = new User();
            user.setMail(data.get("mail").toString());
            user.setHash_password(data.get("hash_password").toString());
            user.setName(data.get("name").toString());
            user.setSurname(data.get("surname").toString());
            user.setLogin(data.get("login").toString());
            user.setPhoto_path(data.get("photo_path").toString());
            user.setAbout_me(data.get("about_me").toString());
            userHandler.addEntity(user);

            if (!plan_typeEnum.contains(data.get("plan_type").toString())) {
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }

            for (String key : optionaryFields) {
                if (!data.containsKey(key)) {
                    data.put(key, null);
                }
            }

            String sql_employee = "INSERT INTO Employees (user_employee_id, company_id, message_template, search_number, plan_type) VALUES (?, ?, ?, ?, ?)";

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
