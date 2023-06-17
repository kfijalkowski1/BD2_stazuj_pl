package com.stazuj_pl.InternshipAd;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path="/internshipAds")
public class InternshipAdControllerFront {
    @GetMapping(value="/allAds")
    public String allUsers(){
        return "InternshipAd/allAds";
    }
}
