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
}
