package com.stazuj_pl.Messages;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/messages")
public class MessagesController {
    @Autowired
    MessagesHandler messagesHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAll() {
        return messagesHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getById(@RequestBody int id) {
        return messagesHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> create(@RequestBody Messages ad) {
        return messagesHandler.addEntity(ad);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> delete(@RequestBody int id) {
        return messagesHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> edit(@RequestBody Map<String, Object> data) {
        return messagesHandler.modifyEntity(data);
    }
}
