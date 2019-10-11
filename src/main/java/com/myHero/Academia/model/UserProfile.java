package com.myHero.Academia.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mobile;

    @Column
    private String secondary_email;

    @OneToOne(mappedBy = "userProfile", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private User user;

    public UserProfile() {}

    // ===return user ===//
    public User getUser() {
        return user;
    }

    //=== parameter user ===//
    public void setUser(User user) {
        this.user = user;
    }

    // ===return Id ===//
    public Long getId() {
        return id;
    }

    //=== parameter id ===//
    public void setId(Long id) {
        this.id = id;
    }

    // ===return Mobile ===//
    public String getMobile() {
        return mobile;
    }

    //=== parameter mobile ===//
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // ===return Secondary_email ===//
    public String getSecondary_email() {
        return secondary_email;
    }

    //=== parameter secondary_email ===//
    public void setSecondary_email(String secondary_email) {
        this.secondary_email = secondary_email;
    }


}
