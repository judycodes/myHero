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

//    @Query("SELECT * FROM Post WHERE post.username = ?0")
//    public Post findAllByUsername(String username);

//    @Query(" {'id' : ?0} ")
//    public List<Post> findPostsByUserId (long user_id);

    @Query(" {'user' : ?0} ")
    public List<Post> findPostsByUser(User user);
}
