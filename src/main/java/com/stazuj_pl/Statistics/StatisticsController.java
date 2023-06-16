//package com.stazuj_pl.Statistics;
//
//
//import com.mysql.cj.xdevapi.JsonArray;
//import com.stazuj_pl.EntityObj;
//import com.stazuj_pl.Posts.Posts;
//import com.stazuj_pl.Posts.PostsHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping(path="/statistics")
//public class StatisticsController {
//    @Autowired
//    StatisticsHandler statisticsHandler;
//
//
//    @PostMapping(
//            value = "/getViesOverTime",
//            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
//            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public ResponseEntity<Map<String, Object>> viesOverTime(@RequestBody Map<String, Object> data) {
//        Map<String, Object> result =  statisticsHandler.getDataOverTime(data);
//        return ResponseEntity.status(200).body(result);
//    }
//
////    @PostMapping(
////        value = "/create",
////        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
////        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
////    public ResponseEntity<HttpStatus> createPost(@RequestBody Posts post) {
////        return postsHandler.addEntity(post);
////    }
////
////    @DeleteMapping(path = "/deleteById")
////    public ResponseEntity<HttpStatus> deletePost(@RequestBody int id) {
////        return postsHandler.deleteById(id);
////    }
////
////    @PostMapping(
////            value = "/edit",
////            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
////            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
////    public ResponseEntity<HttpStatus> editPosts(@RequestBody Map<String, Object> data) {
////        return postsHandler.modifyEntity(data);
////    }
//}
