package com.myHero.Academia.repository;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import com.myHero.Academia.model.UserProfile;

import java.util.List;
import java.util.Optional;


public class PostRepositoryStub implements PostRepository{

    @Override
    public <S extends Post> S save(S entity) {
        Post post = new Post();
        post.getPost_body();

                return (S)post;
    }

    @Override
    public <S extends Post> Iterable<S>  saveAll(Iterable<S> varl) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long along) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long along) {
        return false;
    }

    @Override
    public Iterable<Post> findAll() {
        return null;
    }

    @Override
    public Iterable<Post> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long along) {

    }

    @Override
    public  void delete(Post entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Post> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Post findPostById(Long id) {
        return null;
    }

    @Override
    public List<Post> findPostsByUser(User user) {
        return null;
    }



}
