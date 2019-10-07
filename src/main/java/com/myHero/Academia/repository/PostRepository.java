package com.myHero.Academia.repository;

import com.myHero.Academia.model.Post;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    void deleteById(Long post_id);

}
