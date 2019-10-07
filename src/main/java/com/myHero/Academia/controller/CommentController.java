package com.myHero.Academia.controller;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("{username}/create")
    public Comment createComment(@PathVariable String username,  @RequestBody Comment newComment) {
        return commentService.createComment(newComment, username); }

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
}
