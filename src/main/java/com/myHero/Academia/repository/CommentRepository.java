package com.myHero.Academia.repository;

import com.myHero.Academia.model.Comment;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    void deleteById(Long comment_id);

    @Query(" {'id' : ?0}")
    public Comment findCommentBy (Long comment_id);
    
}
