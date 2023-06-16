package com.stazuj_pl.Employee;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/employees")
public class EmployeeController {
    @Autowired
    EmployeeHandler employeeHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllEmployees() {
        return employeeHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getEmployeeById(@RequestBody int id) {
        return employeeHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createEmployee(@RequestBody Map<String, Object> data) {
        return employeeHandler.addEntity(data);
    }

    @DeleteMapping(path="/deleteById")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestBody int id) {
        return employeeHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editEmployee(@RequestBody Map<String, Object> data) {
        return employeeHandler.modifyEntity(data);
    }
}
