package com.stazuj_pl.TransactionData;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/transactionData")
public class TransactionDataController {
    @Autowired
    TransactionDataHandler transactionDataHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllTransactionData() {
        return transactionDataHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getTransactionDataById(@RequestBody int id) {
        return transactionDataHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createTransactionData(@RequestBody TransactionData transactionData) {
        return transactionDataHandler.addEntity(transactionData);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> deleteTransactionData(@RequestBody int id) {
        return transactionDataHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editTransactionData(@RequestBody Map<String, Object> data) {
        return transactionDataHandler.modifyEntity(data);
    }
}
