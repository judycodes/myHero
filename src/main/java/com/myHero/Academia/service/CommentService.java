package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;

public interface CommentService {

    //=== parameter newComment & return Post ===//
    public Comment createComment(Comment newComment);

    //=== return all comments ===//
    public Iterable<Comment> listAllComments();

}
