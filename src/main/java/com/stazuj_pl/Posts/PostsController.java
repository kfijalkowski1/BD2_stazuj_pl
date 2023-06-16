package com.stazuj_pl.Posts;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/posts")
public class PostsController {
    @Autowired
    PostsHandler postsHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllPosts() {
        return postsHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getPostById(@RequestBody int id) {
        return postsHandler.getById(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createPost(@RequestBody Posts post) {
        return postsHandler.addEntity(post);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> deletePost(@RequestBody int id) {
        return postsHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editPosts(@RequestBody Map<String, Object> data) {
        return postsHandler.modifyEntity(data);
    }
}
