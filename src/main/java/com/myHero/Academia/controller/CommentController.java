package com.myHero.Academia.controller;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("{username}/createOn{post_id}")
    public Comment createComment(@PathVariable String username,  @PathVariable long post_id, @RequestBody Comment newComment) {
        return commentService.createComment(newComment, username, post_id); }

    @GetMapping("/listAllComments")
    public Iterable<Comment> listAllComments() {
        return commentService.listAllComments();
    }

    @DeleteMapping("/delete-{comment_id}")
    public HttpStatus deleteSpecificComment(@PathVariable long comment_id) {
        return commentService.deleteSpecificComment(comment_id);
    }

    @GetMapping("/get-{comment_id}")
    public Comment getSpecificComment(@PathVariable long comment_id) {
        return commentService.getSpecificComment(comment_id);
    }

    @GetMapping("/get-{username}Comments")
    public List<Comment> listUserComment(@PathVariable String username) {
        return commentService.listUserComments(username);
    }
}
