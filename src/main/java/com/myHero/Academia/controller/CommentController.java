package com.myHero.Academia.controller;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create")
    public Comment createComment(@RequestBody Comment newComment) { return commentService.createComment(newComment); }

    @GetMapping("/listAllComments")
    public Iterable<Comment> listAllComments() {
        return commentService.listAllComments();
    }
}
