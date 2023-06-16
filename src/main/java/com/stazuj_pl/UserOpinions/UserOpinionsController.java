package com.stazuj_pl.UserOpinions;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/userOpinions")
public class UserOpinionsController {
    @Autowired
    UserOpinionsHandler userOpinionsHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllUserOpinions() {
        return userOpinionsHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getUserOpinionsById(@RequestBody int id) {
        return userOpinionsHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createUserOpinions(@RequestBody UserOpinions userOpinion) {
        return userOpinionsHandler.addEntity(userOpinion);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> deleteUserOpinions(@RequestBody int id) {
        return userOpinionsHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editUserOpinions(@RequestBody Map<String, Object> data) {
        return userOpinionsHandler.modifyEntity(data);
    }
}
