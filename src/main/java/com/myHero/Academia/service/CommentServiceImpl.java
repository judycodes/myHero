package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Override
    public Comment createComment(Comment newComment) {
        return commentRepository.save(newComment);
    }

    @Override
    public Iterable<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public HttpStatus deleteSpecificComment(long comment_id) {
        commentRepository.deleteById(comment_id);
        return HttpStatus.valueOf(200);
    }

    @Override
    public Comment getSpecificComment(long comment_id) {
        return commentRepository.findCommentById(comment_id);
    }
}
