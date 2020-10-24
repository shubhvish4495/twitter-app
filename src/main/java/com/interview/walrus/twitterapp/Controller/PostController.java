package com.interview.walrus.twitterapp.Controller;

import com.interview.walrus.twitterapp.CommandForm.CreatePostCommandForm;
import com.interview.walrus.twitterapp.CommandForm.LikeCommandForm;
import com.interview.walrus.twitterapp.CommandForm.ListPostsCommandForm;
import com.interview.walrus.twitterapp.Entity.Posts;
import com.interview.walrus.twitterapp.Service.PostService;
import com.interview.walrus.twitterapp.Service.UserService;
import com.interview.walrus.twitterapp.Utility.GeneralUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController {

    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";
    public static final String POST_CREATED_SUCCESSFULLY = "Post created successfully";
    public static final String POST_CONTENT_GREATER_THAN_140_CHARACTERS = "Post content greater than 140 characters";
    public static final String USER_NOT_AUTHENTICATED_PLEASE_LOGIN = "USER NOT AUTHENTICATED. PLEASE LOGIN.";
    public static final String OK = "OK";
    public static final String NO_POSTS_FOUND_FOR_THE_USER = "No posts found for the user";
    public static final String INSUFFICIENT_DATA_PROVIDED = "INSUFFICIENT DATA PROVIDED";
    private Logger logger = Logger.getLogger(PostController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @PostMapping("/like")
    public ResponseEntity<Map<String, Object>> likePost(@RequestBody LikeCommandForm likeCmdFrm){
        ResponseEntity<Map<String, Object>> response;
        String postID =  likeCmdFrm.getPostId();
        String userName = likeCmdFrm.getUserName();

        if (postID.isEmpty() || userName.isEmpty()){
            response = GeneralUtility.formatResponse(Boolean.FALSE, INSUFFICIENT_DATA_PROVIDED, null);
            return response;
        }
        if (!userService.authenticateUser(likeCmdFrm.getUserName(), likeCmdFrm.getToken())){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_AUTHENTICATED_PLEASE_LOGIN, null);
            return response;
        }

        try {
            response = GeneralUtility.formatResponse(Boolean.TRUE,OK,postService.likePosts(Integer.parseInt(postID),userName));
        } catch (Exception e) {
            response =  GeneralUtility.formatResponse(Boolean.FALSE,INTERNAL_ERROR,null);
        }
        return response;
    }

    @PostMapping("/get")
    public ResponseEntity<Map<String, Object>> getPosts(@RequestBody ListPostsCommandForm listPostCmdFrm){
        ResponseEntity<Map<String, Object>> response;

        if (!userService.authenticateUser(listPostCmdFrm.getUserName(), listPostCmdFrm.getToken())){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_AUTHENTICATED_PLEASE_LOGIN, null);
            return response;
        }

        List<Posts> postLists = null;
        try {
            postLists = postService.findPosts(listPostCmdFrm);
            if (postLists.size() == 0) {
                response = GeneralUtility.formatResponse(Boolean.TRUE, NO_POSTS_FOUND_FOR_THE_USER, null);
            } else {
                response = GeneralUtility.formatResponse(Boolean.TRUE, OK, postLists);
            }
        } catch (Exception e) {
            response =  GeneralUtility.formatResponse(Boolean.FALSE,INTERNAL_ERROR,null);
        }

        return response;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPost(@RequestBody CreatePostCommandForm createPostCmdFrm){
        ResponseEntity<Map<String, Object>> response;
        int postLength = GeneralUtility.countCharacters(createPostCmdFrm.getPostContent());

        if (!userService.authenticateUser(createPostCmdFrm.getUserHandle(), createPostCmdFrm.getToken())){
            response = GeneralUtility.formatResponse(Boolean.FALSE, USER_NOT_AUTHENTICATED_PLEASE_LOGIN, null);
            return response;
        }

        if (postLength > 140){
            response = GeneralUtility.formatResponse(Boolean.FALSE, POST_CONTENT_GREATER_THAN_140_CHARACTERS,null);
            return response;
        }

        Boolean isPostCreated = null;
        try {
            isPostCreated = postService.createPosts(createPostCmdFrm);
            response = GeneralUtility.formatResponse(Boolean.TRUE, POST_CREATED_SUCCESSFULLY, null);
        } catch (Exception e) {
            response =  GeneralUtility.formatResponse(Boolean.FALSE, INTERNAL_ERROR,null);
        }

        return response;
    }

}
