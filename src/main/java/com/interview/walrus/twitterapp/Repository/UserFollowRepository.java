package com.interview.walrus.twitterapp.Repository;

import com.interview.walrus.twitterapp.Entity.UserFollowEntity;
import com.interview.walrus.twitterapp.Entity.UserFollowID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowRepository extends CrudRepository<UserFollowEntity, UserFollowID> {
}
