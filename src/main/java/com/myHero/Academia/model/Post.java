package com.myHero.Academia.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

@Entity
@Table(name= "posts")
public class Post {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String post_title;

    @Column
    private String post_body;

    //=== empty constructor ===//
    public Post() {}

    //=== return Id ===//
    public Long getId() {
        return id;
    }

    //=== parameter Id ===//
    public void setId(Long id) {
        this.id = id;
    }

    //=== return post_title ===//
    public String getPost_title() {
        return post_title;
    }

    //=== parameter post_title ===//
    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    //=== return post_body ===//
    public String getPost_body() {
        return post_body;
    }

    //=== parameter post_body ===//
    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

}