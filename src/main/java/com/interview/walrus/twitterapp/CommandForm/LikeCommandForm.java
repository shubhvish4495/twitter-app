package com.interview.walrus.twitterapp.CommandForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Id;

@Data
public class LikeCommandForm {

    @JsonProperty("post_id")
    private String postId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("token")
    private String token;
}
