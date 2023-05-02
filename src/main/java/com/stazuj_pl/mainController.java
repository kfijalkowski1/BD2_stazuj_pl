package com.stazuj_pl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    @GetMapping(value="/")
    public String hello(){
        return "index";
    }
}
