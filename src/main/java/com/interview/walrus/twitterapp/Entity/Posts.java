package com.interview.walrus.twitterapp.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "posts")
public class Posts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    @JsonProperty("post_id")
    private int postID;

    @Column(name="user_handle")
    @JsonProperty("user_handle")
    private String userHandle;

    @Column(name="post_content")
    @JsonProperty("post_content")
    private String postContent;
}
