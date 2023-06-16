package com.stazuj_pl.Student;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/students")
public class StudentController {
    @Autowired
    StudentHandler studentHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllUsers() {
        return studentHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getUserById(@RequestBody int id) {
        return studentHandler.getById(id);
    }

    @PostMapping(
            value = "/getTaggedOffersById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Integer> getTaggedOffersById(@RequestBody int id) {
        return studentHandler.getTaggedOffersById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createUser(@RequestBody Map<String, Object> data) {
        return studentHandler.addEntity(data);
    }

    @DeleteMapping(path="/deleteById")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody int id) {
        return studentHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editUser(@RequestBody Map<String, Object> data) {
        return studentHandler.modifyEntity(data);
    }
}
