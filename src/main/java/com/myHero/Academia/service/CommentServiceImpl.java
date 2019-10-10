package com.myHero.Academia.service;

import com.myHero.Academia.controller.SecurityController;
import com.myHero.Academia.model.Comment;
import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import com.myHero.Academia.repository.CommentRepository;
import com.myHero.Academia.repository.PostRepository;
import com.myHero.Academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    SecurityController securityController;

    @Override
    public Comment createComment(Comment newComment, long post_id) {
        User user = userRepository.findByUsername(securityController.getCurrentUserName());
        newComment.setUser(user);

        Post post = postRepository.findById(post_id).get();
        newComment.setPost(post);

        return commentRepository.save(newComment);
    }

    @Override
    public Iterable<Comment> listAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public ResponseEntity deleteSpecificComment(long comment_id) {
        //username of current user attempting to delete comment
        String currentUsername = securityController.getCurrentUserName();

        if(commentRepository.findCommentById(comment_id).getUser().getUsername().equals(currentUsername)) {
            commentRepository.deleteById(comment_id);
            return new ResponseEntity(HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity(HttpStatus.valueOf(204));
        }

    }

    @Override
    public Comment getSpecificComment(long comment_id) {
        return commentRepository.findCommentById(comment_id);
    }

    @Override
    public List<Comment> listUserComments() {

        User user = userRepository.findByUsername(securityController.getCurrentUserName());

        return commentRepository.findCommentsByUser(user); }

}
