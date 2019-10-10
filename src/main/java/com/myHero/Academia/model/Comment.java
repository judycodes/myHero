package com.myHero.Academia.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Entity
@Table(name= "comments")
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment_body;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
    CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    //=== empty constructor ===//
    public Comment() {}

    //=== return Id ===//
    public Long getId() { return id; }

    //=== parameter Id ===//
    public void setId(Long id) { this.id = id; }

    //=== return comment_body ===//
    public String getComment_body() { return comment_body; }

    //=== parameter comment_body ===//
    public void setComment_body(String comment_body) { this.comment_body = comment_body; }

    public Post getPost() {return post;}

    public void setPost(Post post) {this.post = post;}

    public User getUser(){return user;}

    public void setUser(User user) {this.user = user;}
}


