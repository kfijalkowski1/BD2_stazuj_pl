package com.stazuj_pl.Statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/statistics")
public class StatisticsController {
    @Autowired
    StatisticsHandler statisticsHandler;

    @GetMapping(path="/getAll")
    public List<Map<String, Object>> getUsers() {
        return statisticsHandler.getUsers();
    }

//    @GetMapping(path="/getPostStats")
//    public List<Map<String, Object>> getPosts() {
//        return statisticsHandler.getPosts();
//    }
//
//    @GetMapping(path="/getAdsStats")
//    public List<Map<String, Object>> getAds() {
//        return statisticsHandler.getAds();
//    }
}
