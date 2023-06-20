package com.stazuj_pl.InternshipAd;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/internshipAds")
public class InternshipAdController {
    @Autowired
    InternshipAdHandler InternshipAdHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllAds() {
        return InternshipAdHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getAdById(@RequestBody int id) {
        return InternshipAdHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createAd(@RequestBody InternshipAd ad) {
        return InternshipAdHandler.addEntity(ad);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> deleteAd(@RequestBody int id) {
        return InternshipAdHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editAd(@RequestBody Map<String, Object> data) {
        return InternshipAdHandler.modifyEntity(data);
    }
}
