package com.myHero.Academia.repository;

import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    void deleteById(Long post_id);

    @Query(" {'id' : ?0} ")
    public Post findPostById (Long post_id);

//find user's posts by id
//    @Query(" {'id' : ?0} ")
//    public List<Post> findPostsByUserId (long user_id);

//find user's posts by user object/ username
    public List<Post> findPostsByUser(User user);
}
