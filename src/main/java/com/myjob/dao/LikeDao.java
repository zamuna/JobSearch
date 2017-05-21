package com.myjob.dao;

import com.myjob.model.Like;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public interface LikeDao {
    Integer getLikeStatus(Integer userId,Integer postId);
    Integer getNumberOfLikes(Integer postId);
    Like addLike(Integer userId,Integer postId);
    Like deleteLike(Integer userId,Integer postId);
}
