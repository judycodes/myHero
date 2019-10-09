package com.myHero.Academia.controller;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.UserProfile;
import com.myHero.Academia.repository.PostRepository;
import com.myHero.Academia.service.PostServiceStub;
import com.myHero.Academia.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.bind.annotation.GetMapping;

@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

    private PostController postController;

    @Before
    public void initializePostController() {
        postController = new PostController();
        postController.setPostService(new PostServiceStub());
    }

    @Test
    public void createPost_SavePost_Success() throws Exception{
       Post post = new Post();
       post.setPost_body("I am the bestest!!!!"); // this is meant to work
//        post.setPost_body(null); // this is not meant to work

        Post newPost = postController.createPost(post);

        Assert.assertNotNull(newPost);
        Assert.assertEquals(newPost.getPost_body(), post.getPost_body());
    }

}
