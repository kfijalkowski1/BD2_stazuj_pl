package com.stazuj_pl.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/users")
public class UserControllerFront {
    @GetMapping(value="/allUsers")
    public String allUsers(){
        return "user/allUsers";
    }
}
