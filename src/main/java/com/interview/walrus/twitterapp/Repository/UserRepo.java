package com.interview.walrus.twitterapp.Repository;

import com.interview.walrus.twitterapp.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {

    @Query("from User u where u.userHandle=?1 and u.password=?2")
    User findByUserNamePwd(String username,String pwd);

    User findByUserHandle(String username);
}
