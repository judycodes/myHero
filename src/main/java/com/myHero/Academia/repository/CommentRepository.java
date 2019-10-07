package com.myHero.Academia.repository;

import com.myHero.Academia.model.Comment;
import com.myHero.Academia.model.Post;
import com.myHero.Academia.model.User;
import javafx.geometry.Pos;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    void deleteById(Long comment_id);

    @Query(" {'id' : ?0}")
    public Comment findCommentById (Long comment_id);

    @Query(" {'user' : ?0} ")
    public List<Comment> findCommentsByUser(User user);

    @Query("{'post' : ?0)")
            List<Comment> findCommentByPost(Post post);
}
