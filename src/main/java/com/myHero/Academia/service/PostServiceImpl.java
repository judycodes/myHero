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
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Post createPost(Post newPost, String username) {
        User user = userRepository.findByUsername(username);
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public Iterable<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public HttpStatus deleteSpecificPost(long post_id) {
        postRepository.deleteById(post_id);
        return HttpStatus.valueOf(200);
    }

    @Override
    public Post getSpecificPost(long post_id) {
        return postRepository.findPostById(post_id);
    }

    @Override
    public List<Post> listUserPosts(String username) {
        User user = userRepository.findByUsername(username);
        return postRepository.findPostsByUser(user); }
}
