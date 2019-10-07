package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Override
    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }

}
