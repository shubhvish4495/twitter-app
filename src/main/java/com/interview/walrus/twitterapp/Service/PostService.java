package com.interview.walrus.twitterapp.Service;

import com.interview.walrus.twitterapp.CommandForm.CreatePostCommandForm;
import com.interview.walrus.twitterapp.CommandForm.LikeCommandForm;
import com.interview.walrus.twitterapp.CommandForm.ListPostsCommandForm;
import com.interview.walrus.twitterapp.Entity.Posts;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    public List<Posts> findPosts(ListPostsCommandForm listPostsCommandForm) throws Exception;

    public Boolean createPosts(CreatePostCommandForm createPostCommandForm) throws Exception;

    public String likePosts(int postID, String userHandle) throws Exception;
}
