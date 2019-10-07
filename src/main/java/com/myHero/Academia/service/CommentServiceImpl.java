package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
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


}
