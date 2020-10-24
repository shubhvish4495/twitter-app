package com.interview.walrus.twitterapp.Entity;


import lombok.Data;
import java.io.Serializable;
import java.util.Objects;

@Data
public class UserFollowID implements Serializable {

    private String userID;

    private String followUserID;

}
