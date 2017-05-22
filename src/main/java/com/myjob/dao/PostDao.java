package com.myjob.dao;

import com.myjob.model.Post;

import java.util.List;

/**
 * Created by Rabin Shrestha on 5/21/2017.
 */
public interface PostDao {
    Post get(Integer postId);
    List<Post> getAll(Integer postType,Integer limit,Integer offset);
    Post add(Post post);
    Post update(Integer postId,Post post);
    Post delete(Integer postId);
    Boolean deleteByUserId(Integer userId,Integer postId);

}
