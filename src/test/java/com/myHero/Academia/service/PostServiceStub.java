package com.myHero.Academia.service;

import com.myHero.Academia.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class PostServiceStub implements PostService {

    @Override
    public Post createPost(Post newPost) {
        Post post = new Post();

        post.setPost_body("I am the bestest!!!!");

        if(newPost == null){
            return null;
        }

        return post;

    }

    @Override
    public Iterable<Post> listAllPosts() {
        return null;
    }

    @Override
    public ResponseEntity deleteSpecificPost(long post_id) {
        return null;
    }

    @Override
    public Post getSpecificPost(long post_id) {
        return null;
    }

    @Override
    public List<Post> listUserPosts() {
        return null;
    }

}
