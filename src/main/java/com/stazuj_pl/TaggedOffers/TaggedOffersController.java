package com.stazuj_pl.TaggedOffers;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/taggedOffers")
public class TaggedOffersController {
    @Autowired
    TaggedOffersHandler taggedOffersHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAll() {
        return taggedOffersHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getById(@RequestBody int id) {
        return taggedOffersHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> create(@RequestBody TaggedOffers taggedOffers) {
        return taggedOffersHandler.addEntity(taggedOffers);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> delete(@RequestBody int id) {
        return taggedOffersHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> edit(@RequestBody Map<String, Object> data) {
        return taggedOffersHandler.modifyEntity(data);
    }
}
