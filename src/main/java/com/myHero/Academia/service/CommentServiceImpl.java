package com.myHero.Academia.service;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import com.myHero.Academia.repository.CommentRepository;
import com.myHero.Academia.repository.PostRepository;
import com.myHero.Academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public Comment createComment(Comment newComment, String username, long post_id) {
        User user = userRepository.findByUsername(username);
        Post post = postRepository.findPostById(post_id);
        newComment.setPost(post);
        newComment.setUser(user);
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

    @Override
    public List<Comment> listUserComments(String username) {
        User user = userRepository.findByUsername(username);
        return commentRepository.findCommentsByUser(user); }


//     @Override
//    public List<Comment> listPostComments(Post post) {
//        Post post = postRepository.findPostsByUser()
//     }

}
