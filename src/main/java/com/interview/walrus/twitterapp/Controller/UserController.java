package com.interview.walrus.twitterapp.Controller;

import com.interview.walrus.twitterapp.CommandForm.FindUserCommandForm;
import com.interview.walrus.twitterapp.CommandForm.FollowCommandForm;
import com.interview.walrus.twitterapp.CommandForm.LikeCommandForm;
import com.interview.walrus.twitterapp.CommandForm.ListPostsCommandForm;
import com.interview.walrus.twitterapp.Entity.Posts;
import com.interview.walrus.twitterapp.Entity.User;
import com.interview.walrus.twitterapp.ReturnEntity.UserReturnEntity;
import com.interview.walrus.twitterapp.Service.PostService;
import com.interview.walrus.twitterapp.Service.UserService;
import com.interview.walrus.twitterapp.Utility.GeneralUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    public static final String USERNAME_AND_PASSWORD_IS_REQUIRED = "Username and password is required";
    public static final String USER_NAME_OR_PASSWORD_IS_INCORRECT = "Username or password is incorrect";
    public static final String OK = "OK";
    public static final String FOLLOW_USER_ID_NOT_PROVIDED = "Follow data not provided properly";
    public static final String INTERNAL_ERROR = "INTERNAL ERROR";
    public static final String LOGOUT_SUCCESSFUL = "Logout Successful";
    public static final String USERNAME_IS_REQUIRED = "Username is required";
    public static final String USER_NOT_AUTHENTICATED_PLEASE_LOGIN = "USER NOT AUTHENTICATED. PLEASE LOGIN.";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String LOGOUT_UNSUCCESSFUL_FOR_USER = "Logout unsuccessful for user: ";
    public static final String LOGOUT_SUCCESSFUL_FOR_USER = "Logout successful for user: ";
    public static final String USER_LOGIN_SUCCESSFUL_FOR_USER = "User login successful for user : ";
    public static final String TOKEN = "token";

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam(value="username") String username, @RequestParam(value="pwd") String password){
        ResponseEntity<Map<String, Object>> response;

        if (username.isEmpty() || password.isEmpty()){
            logger.info(USERNAME_AND_PASSWORD_IS_REQUIRED);
            response = GeneralUtility.formatResponse(Boolean.FALSE,USERNAME_AND_PASSWORD_IS_REQUIRED,null);
            return response;
        }
        User user = userService.loginUser(username, password);
        if (user == null) {
            logger.info(USER_NAME_OR_PASSWORD_IS_INCORRECT);
            response = GeneralUtility.formatResponse(Boolean.FALSE,USER_NAME_OR_PASSWORD_IS_INCORRECT, null);
        } else {
            Map<String,String> tokenMap = new HashMap<>();
            tokenMap.put(TOKEN, user.getToken());
            response = GeneralUtility.formatResponse(Boolean.TRUE, OK, tokenMap);
            logger.info(USER_LOGIN_SUCCESSFUL_FOR_USER + user.getUserHandle());
        }
        return response;
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String, Object>> logoout(@RequestParam(value="username") String userName){
        ResponseEntity<Map<String, Object>> response;
        if (userName.isEmpty()){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USERNAME_IS_REQUIRED, null);
        } else {
            boolean isLogOutSuccess = userService.logoutUser(userName);
            if (isLogOutSuccess){
                response = GeneralUtility.formatResponse(Boolean.TRUE, LOGOUT_SUCCESSFUL, null);
                logger.info(LOGOUT_SUCCESSFUL_FOR_USER + userName);
            } else{
                response = GeneralUtility.formatResponse(Boolean.FALSE, INTERNAL_ERROR, null);
                logger.info(LOGOUT_UNSUCCESSFUL_FOR_USER + userName);
            }
        }
        return response;
    }

    @PostMapping("/find")
    public ResponseEntity<Map<String, Object>> findUserByHandle(@RequestBody FindUserCommandForm findUsrCmdFrm){
        ResponseEntity<Map<String, Object>> response;

        if (!userService.authenticateUser(findUsrCmdFrm.getUserHandle(), findUsrCmdFrm.getToken())){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_AUTHENTICATED_PLEASE_LOGIN, null);
            return response;
        }

        try {
            UserReturnEntity foundUser = userService.findUser(findUsrCmdFrm.getFindUserHandle());
            if (foundUser != null){
                response = GeneralUtility.formatResponse(Boolean.TRUE, OK, foundUser);
            } else {
                response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_FOUND, null);
            }
        } catch (Exception e) {
            response = GeneralUtility.formatResponse(Boolean.FALSE, INTERNAL_ERROR, null);
        }

        return response;
    }

    @PostMapping("/follow")
    public ResponseEntity<Map<String, Object>> followUser(@RequestBody FollowCommandForm followCmdFrm) {
        ResponseEntity<Map<String, Object>> response;

        if (followCmdFrm.getFollowUserId().isEmpty() || followCmdFrm.getToken().isEmpty() || followCmdFrm.getUserName().isEmpty()){
            logger.info(FOLLOW_USER_ID_NOT_PROVIDED);
            response = GeneralUtility.formatResponse(Boolean.FALSE,FOLLOW_USER_ID_NOT_PROVIDED, null);
            return response;
        }

        if (!userService.authenticateUser(followCmdFrm.getUserName(), followCmdFrm.getToken())){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_AUTHENTICATED_PLEASE_LOGIN, null);
            return response;
        }

        try {
            response = GeneralUtility.formatResponse(Boolean.TRUE, userService.followUserId(followCmdFrm), null);
        } catch (Exception e) {
            response = GeneralUtility.formatResponse(Boolean.FALSE, INTERNAL_ERROR, null);
        }
        return response;
    }
}
