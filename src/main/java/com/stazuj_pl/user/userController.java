package com.stazuj_pl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;

@Controller
@RequestMapping("/users")
public class userController {
    @Autowired
    static private UserHandler handle = new UserHandler();
    @PostMapping(
            value = "/getUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> postBody(@RequestBody User user) {
        User readUser = handle.getUserInfo(user.getUser_id());
        return ResponseEntity
                .created(URI
                        .create(String.format("/user/%s", readUser)))
                .body(readUser);
    }

}
