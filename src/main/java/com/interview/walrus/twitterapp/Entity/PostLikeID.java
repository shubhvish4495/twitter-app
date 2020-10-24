package com.interview.walrus.twitterapp.Entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class PostLikeID implements Serializable {

    private int postID;

    private String userHandle;

}
