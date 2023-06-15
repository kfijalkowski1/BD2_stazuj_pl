package com.stazuj_pl.AcademicInfo;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/academicInfo")
public class AcademicInfoController {
    @Autowired
    AcademicInfoHandler academicInfoHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllAcademicInfo() {
        return academicInfoHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getAcademicInfoById(@RequestBody int id) {
        return academicInfoHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createAcademicInfo(@RequestBody AcademicInfo academicInfo) {
        return academicInfoHandler.addEntity(academicInfo);
    }

    @DeleteMapping(path="/deleteById")
    public ResponseEntity<HttpStatus> deleteAcademicInfo(@RequestBody int id) {
        return academicInfoHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editAcademicInfo(@RequestBody Map<String, Object> data) {
        return academicInfoHandler.modifyEntity(data);
    }
}
