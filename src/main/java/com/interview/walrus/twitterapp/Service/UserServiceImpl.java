package com.interview.walrus.twitterapp.Service;

import com.interview.walrus.twitterapp.CommandForm.FollowCommandForm;
import com.interview.walrus.twitterapp.Entity.User;
import com.interview.walrus.twitterapp.Entity.UserFollowEntity;
import com.interview.walrus.twitterapp.Repository.UserFollowRepository;
import com.interview.walrus.twitterapp.Repository.UserRepo;
import com.interview.walrus.twitterapp.ReturnEntity.UserReturnEntity;
import com.interview.walrus.twitterapp.Utility.GeneralUtility;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserFollowRepository followRepository;

    @Override
    public User loginUser(String username, String pwd) {
        User user = new User();
        try {
            user = repo.findByUserNamePwd(username,pwd);
            if (user!= null && user.getIsLoggedOut() == 1){
                user.setToken(GeneralUtility.generateRandomToken());
                logger.info("Token generated for user " + user.getUserHandle() + " is: " + user.getToken());
                user.setIsLoggedOut(0);
                repo.save(user);
            }
        } catch(Exception e){
            logger.error("Error while fetching data from db : " + e.getMessage());
        }

        return user;
    }

    @Override
    public boolean logoutUser(String username) {
        boolean isSuccessful;
        try{
            User byUserHandle = repo.findByUserHandle(username);
            byUserHandle.setIsLoggedOut(1);
            repo.save(byUserHandle);
            isSuccessful = true;
        } catch (Exception e){
            logger.error("Error while fetching data for logout");
            isSuccessful = false;
        }
        return isSuccessful;
    }

    @Override
    public String followUserId(FollowCommandForm followCommandForm) throws Exception {

        try{
            User followUser = repo.findByUserHandle(followCommandForm.getFollowUserId());
            if (followUser != null){
                UserFollowEntity userFollow = new UserFollowEntity();
                userFollow.setFollowUserID(followCommandForm.getFollowUserId());
                userFollow.setUserID(followCommandForm.getUserName());
                followRepository.save(userFollow);
            } else {
                logger.info(" User not found for the follow id " + followCommandForm.getFollowUserId());
                return "User not found for the follow id";
            }
        } catch (Exception e){
            logger.error("Error occurred while follow user API : " + e.getMessage());
            throw new Exception();
        }
        return "Follow successful";
    }

    public boolean authenticateUser(String username, String token){
        boolean isAuthenticated;
        User userTemp = repo.findByUserHandle(username);
        if (userTemp!= null && userTemp.getToken().equals(token) && userTemp.getIsLoggedOut() == 0){
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    @Override
    public UserReturnEntity findUser(String findUserHandle) throws Exception {
        User foundUser = new User();
        UserReturnEntity returnEntity = new UserReturnEntity();

        try {
            foundUser = repo.findByUserHandle(findUserHandle);
            returnEntity.setFirstName(foundUser.getFirstName());
            returnEntity.setId(foundUser.getId());
            returnEntity.setLastName(foundUser.getLastName());
            returnEntity.setUserHandle(foundUser.getUserHandle());
        } catch (Exception e) {
            logger.error("Error while finding user " + e.getMessage());
            throw new Exception();
        }
        return returnEntity;
    }
}
