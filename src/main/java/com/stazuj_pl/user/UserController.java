package com.stazuj_pl.user;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    UserHandler userHandler;

    @GetMapping(path="/getAll")
    public List<User> getAllUsers() {
        return userHandler.getAllUsers();
    }

    @GetMapping(path="/getUserById/{id}")
    public User getUserById(@PathVariable int id) {
        return userHandler.getUserById(id);
    }

    @PostMapping(
        value = "/createUser",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createUser(@RequestBody User user) {
        return userHandler.createUser(user);
    }

    @DeleteMapping(path="deleteUser/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        return userHandler.deleteUser(id);
    }

    @PostMapping(
            value = "/editUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editUser(@RequestBody Map<String, Object> data) {
        return userHandler.setUserField(data);
    }
}
