package com.interview.walrus.twitterapp.Service;

import com.interview.walrus.twitterapp.CommandForm.CreatePostCommandForm;
import com.interview.walrus.twitterapp.CommandForm.ListPostsCommandForm;
import com.interview.walrus.twitterapp.Entity.PostLike;
import com.interview.walrus.twitterapp.Entity.Posts;
import com.interview.walrus.twitterapp.Repository.PostLikeRepository;
import com.interview.walrus.twitterapp.Repository.PostRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static Logger logger = Logger.getLogger(PostServiceImpl.class);

    @Autowired
    PostRepository postRepo;

    @Autowired
    PostLikeRepository postLikeRepo;

    @Override
    public List<Posts> findPosts(ListPostsCommandForm listPostsCommandForm) throws Exception {
        List<Posts> postList = new ArrayList<>();
        try {
            postList = postRepo.findByUserHandle(listPostsCommandForm.getListUserName());
        } catch (Exception e) {
            logger.error("Could not find posts " + e.getMessage());
            throw new Exception();
        }

        return postList;
    }

    @Override
    public Boolean createPosts(CreatePostCommandForm createPostCommandForm) throws Exception {
        boolean isPostCreated =  false;
        try{
            Posts post = new Posts();
            post.setPostContent(createPostCommandForm.getPostContent());
            post.setUserHandle(createPostCommandForm.getUserHandle());
            postRepo.save(post);
            isPostCreated = true;

        } catch(Exception e){
            logger.error("Error while creating post: " + e.getMessage());
            throw new Exception();
        }
        return isPostCreated;
    }

    @Override
    public String likePosts(int postID, String userHandle) throws Exception {
        try{
            PostLike postLike = new PostLike();
            postLike.setPostID(postID);
            postLike.setUserHandle(userHandle);
            postLikeRepo.save(postLike);
        } catch(Exception e) {
            logger.error("Error " + e.getMessage());
            throw new Exception();
        }

        return "POST LIKE SUCCESS";
    }
}
