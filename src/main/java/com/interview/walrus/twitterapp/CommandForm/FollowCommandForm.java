package com.interview.walrus.twitterapp.CommandForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FollowCommandForm {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("token")
    private String token;

    @JsonProperty("follow_user_id")
    private String followUserId;
}
