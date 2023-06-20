package com.stazuj_pl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="admin")
public class AdminController {
    @GetMapping(value="")
    public String indexAdmin() { return "admin/index"; }

    @GetMapping(value="user/allUsers")
    public String allUsers(){
        return "user/allUsers";
    }

    @GetMapping(value="user/createUser")
    public String createUser() { return "user/createUser"; }

    @GetMapping(value="statistics")
    public String getStatistics() { return "admin/statistics"; }
}

