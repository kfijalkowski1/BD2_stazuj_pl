package com.stazuj_pl.Comments;


import com.stazuj_pl.EntityObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/comments")
public class CommentsController {
    @Autowired
    CommentsHandler commentsHandler;

    @GetMapping(path="/getAll")
    public List<EntityObj> getAllAdresses() {
        return commentsHandler.getAll();
    }

    @PostMapping(
            value = "/getById",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityObj getCommentById(@RequestBody int id) {
        return commentsHandler.getById(id);
    }

    @PostMapping(
            value = "/getCommentsOnPost",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Integer> getCommentsOnPost(@RequestBody int id) {
        return commentsHandler.getCommentsOnPost(id);
    }

    @PostMapping(
        value = "/create",
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> createComment(@RequestBody Comments ad) {
        return commentsHandler.addEntity(ad);
    }

    @DeleteMapping(path = "/deleteById")
    public ResponseEntity<HttpStatus> deleteComment(@RequestBody int id) {
        return commentsHandler.deleteById(id);
    }

    @PostMapping(
            value = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<HttpStatus> editComment(@RequestBody Map<String, Object> data) {
        return commentsHandler.modifyEntity(data);
    }
}
