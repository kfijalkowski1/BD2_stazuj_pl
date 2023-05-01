package com.stazuj_pl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mainController {

    @GetMapping(value="/")
    public String hello(){
        return "index";
    }
}
