package com.interview.walrus.twitterapp.CommandForm;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class FindUserCommandForm {

    @JsonProperty("user_name")
    private String userHandle;

    @JsonProperty("find_user_handle")
    private String findUserHandle;

    private String token;
}
