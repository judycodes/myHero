package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface PostService {

    //=== parameter newPost & return Post ===//
    public Post createPost(Post newPost, String username);

    //=== return all posts ===//
    public Iterable<Post> listAllPosts();

    //=== parameter post_id & return OK message ===//
    public HttpStatus deleteSpecificPost(long post_id);

    //=== parameter post_id & return specific post ===//
    public Post getSpecificPost(long post_id);

    public List<Post> listUserPosts(String username);
}
