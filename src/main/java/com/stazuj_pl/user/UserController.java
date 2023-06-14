package com.stazuj_pl.user;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserHandler userHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllUsers() {
        return userHandler.getAll();
    }

    @PostMapping(
            value = "/getUserById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getUserById(@PathVariable User user) {
        return userHandler.getById(user.getUser_id());
    }

    @PostMapping(
        value = "/createUser",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        return userHandler.addEntity(user);
    }

    @DeleteMapping(path="deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        return userHandler.deleteById(id);
    }

    @PostMapping(
            value = "/editUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editUser(@RequestBody Map<String, Object> data) {
        return userHandler.modifyEntity(data);
    }
}
