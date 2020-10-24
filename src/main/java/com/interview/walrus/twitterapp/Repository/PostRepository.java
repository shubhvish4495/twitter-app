package com.interview.walrus.twitterapp.Repository;

import com.interview.walrus.twitterapp.Entity.Posts;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Posts,Integer> {

    @Query("from Posts p where p.userHandle=?1")
    public List<Posts> findByUserHandle(String userName);
}
