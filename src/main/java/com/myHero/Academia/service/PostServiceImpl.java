package com.myHero.Academia.service;

import com.myHero.Academia.controller.SecurityController;
import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import com.myHero.Academia.repository.PostRepository;
import com.myHero.Academia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    SecurityController securityController;

    @Override
    public Post createPost(Post newPost) {
        String username = securityController.getCurrentUserName();
        User user = userRepository.findByUsername(username);
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public Iterable<Post> listAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public ResponseEntity deleteSpecificPost(long post_id) {
        String currentUsername = securityController.getCurrentUserName();

        if(postRepository.findPostById(post_id).getUser().getUsername().equals(currentUsername)){
            postRepository.deleteById(post_id);
            return new ResponseEntity(HttpStatus.valueOf(200));
        }else{
            return new ResponseEntity(HttpStatus.valueOf(204));
        }

    }

    @Override
    public Post getSpecificPost(long post_id) {
        return postRepository.findById(post_id).get();
    }

    @Override
    public List<Post> listUserPosts() {
        String username = securityController.getCurrentUserName();
        User user = userRepository.findByUsername(username);
        return postRepository.findPostsByUser(user); }
}
