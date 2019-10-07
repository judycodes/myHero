package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CommentService {

    //=== parameter newComment & return Post ===//
    public Comment createComment(Comment newComment, String username, long post_id);

    //=== return all comments ===//
    public Iterable<Comment> listAllComments();

    public Iterable<Comment> listAllPostComments(long post_id);

    //=== parameter comment_id & return OK message ===//
    public HttpStatus deleteSpecificComment(long comment_id);

    //=== parameter post_id & return specific comment ===//
    public Comment getSpecificComment(long comment_id);

    public List<Comment> listUserComments(long post_id, String username);

//    public List<Comment> listPostComments(Post post);

}
