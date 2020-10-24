package com.interview.walrus.twitterapp.CommandForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ListPostsCommandForm {

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("list_user_name")
    private String listUserName;

    private String token;
}
