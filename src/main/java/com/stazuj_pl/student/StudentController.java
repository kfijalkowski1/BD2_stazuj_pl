package com.stazuj_pl.student;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/student")
public class StudentController {
    @Autowired
    StudentHandler userHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllUsers() {
        return userHandler.getAll();
    }

    @PostMapping(
            value = "/getStudentById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getUserById(@RequestBody int id) {
        return userHandler.getById(id);
    }

    @PostMapping(
        value = "/createStudent",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createUser(@RequestBody Map<String, Object> data) {
        return userHandler.addEntity(data);
    }

    @DeleteMapping(path="/deleteStudent")
    public ResponseEntity<HttpStatus> deleteUser(@RequestBody int id) {
        return userHandler.deleteById(id);
    }

    @PostMapping(
            value = "/editStudent",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editUser(@RequestBody Map<String, Object> data) {
        return userHandler.modifyEntity(data);
    }
}
