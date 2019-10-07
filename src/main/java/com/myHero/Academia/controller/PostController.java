package com.myHero.Academia.controller;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import com.myHero.Academia.repository.UserRepository;
import com.myHero.Academia.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("{username}/create")
    public Post createPost(@PathVariable String username, @RequestBody Post newPost) {
        return postService.createPost(newPost, username);
    }

    @GetMapping("/listAllPosts")
    public Iterable<Post> listAllPosts() {
        return postService.listAllPosts();
    }

    @DeleteMapping("/delete-{post_id}")
    public HttpStatus deleteSpecificPost(@PathVariable long post_id) {
        return postService.deleteSpecificPost(post_id);
    }

    @GetMapping("/get-{post_id}")
    public Post getSpecificPost(@PathVariable long post_id) {
        return postService.getSpecificPost(post_id);
    }

    @GetMapping("/get-{username}Posts")
    public List<Post> listUserPosts(@PathVariable String username) { return postService.listUserPosts(username); }
}
