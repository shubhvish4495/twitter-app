package com.interview.walrus.twitterapp.CommandForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreatePostCommandForm {

    @JsonProperty("user_name")
    private String userHandle;

    private String token;

    @JsonProperty("post_content")
    private String postContent;
}
