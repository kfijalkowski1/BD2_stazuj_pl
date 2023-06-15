package com.stazuj_pl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class offersController {
    @GetMapping(value="offers")
    public String hello(){
        return "offers";
    }
}
