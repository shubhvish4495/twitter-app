package com.interview.walrus.twitterapp.Service;

import com.interview.walrus.twitterapp.CommandForm.FollowCommandForm;
import com.interview.walrus.twitterapp.Entity.User;
import com.interview.walrus.twitterapp.ReturnEntity.UserReturnEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User loginUser(String username, String pwd);

    public boolean logoutUser(String username);

    public String followUserId(FollowCommandForm followCommandForm) throws Exception;

    public boolean authenticateUser(String username, String token);

    public UserReturnEntity findUser(String findUserHandle) throws Exception;
}
