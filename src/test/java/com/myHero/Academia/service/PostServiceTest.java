package com.myHero.Academia.service;

import com.myHero.Academia.controller.PostController;
import com.myHero.Academia.model.User;
import com.myHero.Academia.model.Post;
import com.myHero.Academia.repository.PostRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PostServiceTest {
    private PostController postController;
    @Before
    public void initializePost() {
        postController = new PostController();
        postController.setPostService(new PostServiceStub());
    }

    @Test
    public void createPost(){
        Post post = new Post();
        post.setPost_body("I am the bestest!!!!");

        Post newPost = postController.createPost(post); // meant to work
//        Post newPost = postController.createPost(null); // not meant to work
        System.out.println(newPost);
        Assert.assertNotNull(newPost);
        Assert.assertEquals(newPost.getPost_body(), post.getPost_body());
    }

}
