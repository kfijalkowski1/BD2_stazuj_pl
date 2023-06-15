package com.stazuj_pl.address;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/address")
public class AddressController {
    @Autowired
    AddressHandler addressHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllAddresses() {
        return addressHandler.getAll();
    }

    @PostMapping(
            value = "/getAddressById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getAddressById(@RequestBody int id) {
        return addressHandler.getById(id);
    }

    @PostMapping(
        value = "/createAddress",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createAddress(@RequestBody Address address) {
        return addressHandler.addEntity(address);
    }

    @DeleteMapping(path="/deleteAddress")
    public ResponseEntity<HttpStatus> deleteAddress(@RequestBody int id) {
        return addressHandler.deleteById(id);
    }

    @PostMapping(
            value = "/editAddress",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editAddress(@RequestBody Map<String, Object> data) {
        return addressHandler.modifyEntity(data);
    }
}
