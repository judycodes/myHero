package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import org.springframework.http.HttpStatus;

public interface PostService {

    //=== parameter newPost & return Post ===//
    public Post createPost(Post newPost);

    //=== return all posts ===//
    public Iterable<Post> listAllPosts();
}
