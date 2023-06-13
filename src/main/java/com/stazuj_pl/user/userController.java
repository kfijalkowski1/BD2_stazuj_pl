package com.stazuj_pl.user;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;

@Controller
public class userController {
    static private UserHandler handle = new UserHandler();
    @PostMapping(
            value = "/getUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> postBody(@RequestBody Integer user_id) {
        User readUser = handle.getUserInfo(user_id);
        return ResponseEntity
                .created(URI
                        .create(String.format("/user/%s", readUser)))
                .body(readUser);
    }

}
