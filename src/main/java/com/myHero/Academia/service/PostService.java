package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PostService {

    //=== parameter newPost & return Post ===//
    public Post createPost(Post newPost);

    //=== return all posts ===//
    public Iterable<Post> listAllPosts();

    //=== parameter post_id & return OK message ===//
    public ResponseEntity deleteSpecificPost(long post_id);

    //=== parameter post_id & return specific post ===//
    public Post getSpecificPost(long post_id);

    public List<Post> listUserPosts();
}
