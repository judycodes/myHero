package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import org.springframework.http.HttpStatus;

public interface CommentService {

    //=== parameter newComment & return Post ===//
    public Comment createComment(Comment newComment);

    //=== return all comments ===//
    public Iterable<Comment> listAllComments();

    //=== parameter comment_id & return OK message ===//
    public HttpStatus deleteSpecificComment(long comment_id);

}
