package com.interview.walrus.twitterapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="user_post_like")
@IdClass(PostLikeID.class)
public class PostLike implements Serializable {

    @Id
    @Column(name="post_id")
    private int postID;

    @Id
    @Column(name="user_handle")
    private String userHandle;
}
