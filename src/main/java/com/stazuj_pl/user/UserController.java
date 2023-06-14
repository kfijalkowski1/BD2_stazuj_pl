package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    UserHandler userHandler;

    @GetMapping(path="/getAll")
    public List<User> getAllUsers() {
        List<User> result = userHandler.getAllUsers();
        System.out.println(result);
        return result;
    }

    @PostMapping(
            value = "/getUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> postBody(@RequestBody User user) {
        User readUser = userHandler.getUserInfo(user.getUser_id());
        return ResponseEntity
                .created(URI
                        .create(String.format("/user/%s", readUser)))
                .body(readUser);
    }

}
