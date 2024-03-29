package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {

    //=== parameter newComment & return Post ===//
    public Comment createComment(Comment newComment, long post_id);

    //=== return all comments ===//
    public Iterable<Comment> listAllComments();

    //=== parameter comment_id & return OK message ===//
    public ResponseEntity deleteSpecificComment(long comment_id);

    //=== parameter post_id & return specific comment ===//
    public Comment getSpecificComment(long comment_id);

    public List<Comment> listUserComments();

}
