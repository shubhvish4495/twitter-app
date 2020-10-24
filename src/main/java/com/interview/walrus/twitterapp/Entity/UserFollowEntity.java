package com.interview.walrus.twitterapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_follow")
@IdClass(UserFollowID.class)
public class UserFollowEntity implements Serializable {

    @Id
    @Column(name="user_id")
    private String userID;

    @Id
    @Column(name="follow_user_id")
    private String followUserID;
}
