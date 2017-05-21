package com.myjob.dao.impl;

import com.myjob.dao.LikeDao;
import com.myjob.model.Like;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public class LikeDaoImpl implements LikeDao {
    // check if user have already liked the post or not

    /**
     *
     * @param userId
     * @param postId
     * @return returns the LikeId if found the like
     */
    @Override
    public Integer getLikeStatus(Integer userId, Integer postId) {
        return null;
    }

    @Override
    public Integer getNumberOfLikes(Integer postId) {
        return null;
    }

    @Override
    public Like addLike(Integer userId, Integer postId) {
        return null;
    }

    @Override
    public Like deleteLike(Integer userId, Integer postId) {
        return null;
    }
}
