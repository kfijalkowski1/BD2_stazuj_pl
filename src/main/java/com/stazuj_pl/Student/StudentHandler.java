package com.stazuj_pl.Student;


import com.stazuj_pl.CrudHandler;
import com.stazuj_pl.EntityObj;
import com.stazuj_pl.Files.Files;
import com.stazuj_pl.Files.FilesHandler;
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

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Repository
public class StudentHandler extends CrudHandler {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserHandler userHandler;
    @Autowired
    TaggedOffersHandler taggedOffersHandler;
    @Autowired
    FilesHandler filesHandler;
    @Autowired
    TransactionDataHandler transactionDataHandler;

    StudentHandler() {
        this.tableName = "Students";
        this.safeName = "StudentsPublicData";
        this.safeRowMapper = new BeanPropertyRowMapper<>(CensoredStudents.class);
        this.tableMainKey = "student_id";
        this.rowMapper = new BeanPropertyRowMapper<>(Student.class);
        this.modifiableKeys = Arrays.asList("academic_year", "looking_for_job", "keywords", "academic_info_id");
    }

    @Override
    public ResponseEntity<HttpStatus> addEntity(EntityObj e) {
        return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
    }

    public List<Integer> getTaggedOffersById(int id) {
        List<EntityObj> listOfTaggedOffers = taggedOffersHandler.getAll();
        List<Integer> listOfOffersId = new ArrayList<>(List.of());

        for(EntityObj obj : listOfTaggedOffers) {
            TaggedOffers taggedOffers = (TaggedOffers) obj;
            if(taggedOffers.getUser_id() == id) {
                listOfOffersId.add(taggedOffers.getTagged_offer_id());
            }
        }
        return listOfOffersId;
    }

    public List<Integer> getFiles(int id) {
        List<EntityObj> listOfFiles = filesHandler.getAll();
        List<Integer> listOfFilesId = new ArrayList<>(List.of());

        for(EntityObj obj : listOfFiles) {
            Files file = (Files) obj;
            if(file.getUser_id() == id) {
                listOfFilesId.add(file.getFile_id());
            }
        }
        return listOfFilesId;
    }

    public List<Integer> getTransactionData(int id) {
        List<Integer> listOfTransactionDataId = new ArrayList<>(List.of());
        List<EntityObj> listOfTransactionData = transactionDataHandler.getAll();
        List<Integer> listOfFilesId = getFiles(id);

        for (EntityObj obj : listOfTransactionData) {
            TransactionData transactionData = (TransactionData) obj;

            if(listOfFilesId.contains(transactionData.getCv_id())) {
                listOfTransactionDataId.add(transactionData.getTransaction_data_id());
            }

        }
        return listOfTransactionDataId;
    }
    @Override
    public EntityObj getById(int entity_id) {
        String sql = String.format("SELECT * FROM %s where user_student_id = ?", safeName, tableMainKey);
        List<EntityObj> entityData = jdbcTemplate.query(sql, (BeanPropertyRowMapper) safeRowMapper, entity_id);
        return (entityData.size() != 1) ? null : entityData.get(0);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteById(int entity_id) {
        EntityObj obj = getById(entity_id);
        Student student = (Student) obj;
        int student_id = student.getStudent_id();
        String delete_student = String.format("DELETE from %s where %s = ?;", tableName, tableMainKey);
        int dataAffected = jdbcTemplate.update(delete_student, student_id);
        if (dataAffected != 1) {
            return new ResponseEntity<HttpStatus>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> addEntity(Map<String, Object> data) {
        try {
            List<String> obligatoryFields = Arrays.asList("hash_password", "name", "surname", "login", "academic_info_id");
            List<String> optionaryFields = Arrays.asList("mail", "photo_path", "about_me", "academic_year", "looking_for_job", "keywords");

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

            for (String key : optionaryFields) {
                if (!data.containsKey(key)) {
                    data.put(key, null);
                }
            }

            String sql_student = "INSERT INTO Students (academic_info_id, user_student_id, academic_year, looking_for_job, keywords) VALUES (?, ?, ?, ?, ?)";

            int user_id = userHandler.getIdByUniqueField(data.get("login").toString());
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
            } else {
                userHandler.deleteById(user_id);
                return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
            }
        } catch (DataAccessException e) {
            return new ResponseEntity<HttpStatus>(HttpStatus.I_AM_A_TEAPOT);
        }
    }


}
