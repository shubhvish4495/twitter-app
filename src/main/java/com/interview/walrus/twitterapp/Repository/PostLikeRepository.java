package com.interview.walrus.twitterapp.Repository;

import com.interview.walrus.twitterapp.Entity.PostLike;
import com.interview.walrus.twitterapp.Entity.PostLikeID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends CrudRepository<PostLike, PostLikeID> {
}
