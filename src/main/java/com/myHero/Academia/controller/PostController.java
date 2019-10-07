package com.myHero.Academia.controller;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create")
    public Post createPost(@RequestBody Post newPost) {
        return postService.createPost(newPost);
    }

    @GetMapping("/listAllPosts")
    public Iterable<Post> listAllPosts() {
        return postService.listAllPosts();
    }

    @DeleteMapping("/delete-{post_id}")
    public HttpStatus deleteSpecificPost(@PathVariable long post_id) {
        return postService.deleteSpecificPost(post_id);
    }
}