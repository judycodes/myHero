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

}


