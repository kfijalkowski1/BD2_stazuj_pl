package com.stazuj_pl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class loginController {
    @GetMapping(value="/login")
    public String login(){
        return "login";
    }
}
