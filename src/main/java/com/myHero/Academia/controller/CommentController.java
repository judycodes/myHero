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

    @PostMapping("/createOn{post_id}")
    public Comment createComment(@PathVariable long post_id, @RequestBody Comment newComment) {
        return commentService.createComment(newComment, post_id); }

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

    @GetMapping("/listAllPostComments/{post_id}")
    public Iterable<Comment> listAllPostComments(@PathVariable long post_id) {
        return commentService.listAllPostComments(post_id);
    }

    @GetMapping("/listUserComments")
    public List<Comment> listUserComment() {
        return commentService.listUserComments();
    }

}
