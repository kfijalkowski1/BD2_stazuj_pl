package com.stazuj_pl.Posts;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="posts")
public class PostsControllerFront {
    @GetMapping(value="/publish_post")
    public String allUsers(){
        return "posts/publish_post";
    }

    @GetMapping(value="/allPosts")
    public String allPosts(){
        return "posts/allPosts";
    }
}


